package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.*;
import com.huashan.yebserver.domain.vo.AdminParameter;
import com.huashan.yebserver.domain.vo.AdminVo;
import com.huashan.yebserver.domain.vo.MenuVo;
import com.huashan.yebserver.mapper.*;
import com.huashan.yebserver.service.IAdminService;
import com.huashan.yebserver.utils.JwtUtil;
import com.huashan.yebserver.utils.RedisUtil;
import io.github.penghuaizheng.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public AjaxResult login(AdminParameter adminParameter, HttpServletRequest request) {
//        String captcha = (String) request.getSession().getAttribute("captcha");
        String remoteAddr = request.getRemoteAddr();
        String captcha = (String) redisUtil.get(remoteAddr + ":captcha");
        if (!adminParameter.getCode().equalsIgnoreCase(captcha)) {
            return AjaxResult.error("验证码错误");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminParameter.getUsername());
        if (!passwordEncoder.matches(adminParameter.getPassword(), userDetails.getPassword())) {
            return AjaxResult.error("账号或密码错误");
        }
        if (!userDetails.isEnabled()) {
            return AjaxResult.error("账号已被禁用");
        }
        if (!userDetails.isAccountNonLocked()) {
            return AjaxResult.error("账号已被锁定");
        }
        if (!userDetails.isAccountNonExpired()) {
            return AjaxResult.error("账号已过期");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            return AjaxResult.error("密码已过期");
        }


        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", jwtUtil.createToken(userDetails));
        AdminVo adminVo = (AdminVo) userDetails;
        redisUtil.set("security:" + adminVo.getUsername(), adminVo, 60 * 60 * 24 * 7);
        return AjaxResult.success("登录成功", tokenMap);
    }

    @Override
    public AjaxResult logout() {
        //ToDo: 清除redis保存的用户信息
        redisUtil.del("security:" + ((AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return AjaxResult.success("退出成功", null);
    }

    @Override
    public AjaxResult getInfo() {
        AdminVo adminVo = (AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ObjectUtils.isEmpty(adminVo)) {
            return AjaxResult.error("获取用户信息失败");
        }
        adminVo.setPassword(null);
        return AjaxResult.success("获取用户信息成功", adminVo);
    }

    @Override
    public AjaxResult getMenusByAdminId() {
        List<MenuVo> parentMenuVos = (List<MenuVo>) redisUtil.get("menus:" + ((AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if (ObjectUtils.isEmpty(parentMenuVos)) {
            AdminVo adminVo = (AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Set<Role> roles = adminVo.getRoles();
            List<Integer> roleIds = EntityUtils.toList(roles, Role::getId);
            List<MenuRole> menuRoles = menuRoleMapper.selectList(Wrappers.lambdaQuery(MenuRole.class).in(MenuRole::getRid, roleIds));
            List<Integer> menuIds = EntityUtils.toList(menuRoles, MenuRole::getMid);
            if (menuIds.size() == 0) {
                List<MenuVo> list = new ArrayList<>();
                return AjaxResult.success("获取菜单成功", list);
            }
            List<Menu> childrenMenus = menuMapper.selectBatchIds(menuIds);
            List<Integer> parentIds = EntityUtils.toList(childrenMenus, Menu::getParentid);
            List<Menu> parentMenus = menuMapper.selectBatchIds(parentIds);
            parentMenuVos = EntityUtils.toList(parentMenus, MenuVo::new);
            for (MenuVo parentMenuVo : parentMenuVos) {
                Integer id = parentMenuVo.getId();
                List<Menu> menus = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class).eq(Menu::getParentid, id).in(Menu::getId, menuIds));

                List<MenuVo> menuVos = EntityUtils.toList(menus, MenuVo::new);
                parentMenuVo.setChildren(menuVos);
            }
            redisUtil.set("menus:" + adminVo.getUsername(), parentMenuVos, 60 * 60 * 24 * 7);
        }
        return AjaxResult.success("获取菜单成功", parentMenuVos);
    }

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Admin admin) {
        IPage<Admin> page = pageEntity.toPage();
        if (StringUtils.isNotBlank(admin.getName())) {
            page = adminMapper.selectPage(page, Wrappers.<Admin>lambdaQuery().like(Admin::getName, admin.getName()));
        } else {
            page = adminMapper.selectPage(page, null);
        }
        List<Admin> admins = page.getRecords();
        List<AdminVo> adminVos = EntityUtils.toList(admins, AdminVo::new);
        extracted(adminVos);
        long total = page.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("adminVos", adminVos);
        map.put("total", total);
        return AjaxResult.success("获取管理员成功", map);
    }


    @Override
    public AjaxResult editById(AdminVo adminVo) {
        Set<Role> roles = adminVo.getRoles();

        List<Integer> roleIds = EntityUtils.toList(roles, Role::getId);
        List<Role> roleList = roleMapper.selectBatchIds(roleIds);
        Admin admin = EntityUtils.toObj(adminVo, Admin::new);
        AdminVo oldAdminVo = (AdminVo) redisUtil.get("security:" + admin.getUsername());
        Admin oldAdmin = EntityUtils.toObj(oldAdminVo, Admin::new);
        if (ObjectUtils.isEmpty(oldAdminVo)) {
            oldAdmin = adminMapper.selectById(adminVo.getId());
        } else {
            redisUtil.del("menus:" + oldAdmin.getUsername());
            Set<Role> set = EntityUtils.toSet(roleList);
            oldAdminVo.setRoles(set);
            redisUtil.set("security:" + oldAdminVo.getUsername(), oldAdminVo, 60 * 60 * 24 * 7);
        }
        if (admin.getEnable() != oldAdmin.getEnable()) {
            redisUtil.del("security:" + admin.getUsername());
        }
        int i = adminMapper.updateById(admin);


        // 先删除原有的角色
        adminRoleMapper.delete(Wrappers.<AdminRole>lambdaQuery().eq(AdminRole::getAdminid, adminVo.getId()));
        // 再添加新的角色
        for (Integer roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminid(adminVo.getId());
            adminRole.setRid(roleId);
            adminRoleMapper.insert(adminRole);
        }
        return AjaxResult.success("修改管理员成功", null);
    }

    @Override
    public AjaxResult deleteBatchByIds(Integer[] ids) {
        int i = adminMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除管理员成功", null);
        } else {
            return AjaxResult.error("删除管理员失败");
        }
    }

    @Override
    public AjaxResult edit(Admin admin) {
        AdminVo adminVo = (AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int i = adminMapper.updateById(admin);
        if (i > 0) {
            adminVo.setName(admin.getName());
            adminVo.setPhone(admin.getPhone());
            adminVo.setAddress(admin.getAddress());
            adminVo.setTelephone(admin.getTelephone());
            redisUtil.set("security:" + adminVo.getUsername(), adminVo, 60 * 60 * 24 * 7);
            return AjaxResult.success("修改个人信息成功", null);
        } else {
            return AjaxResult.error("修改个人信息失败");
        }
    }

    @Override
    public AjaxResult editPassword(AdminParameter adminParameter) {
        AdminVo adminVo = (AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!passwordEncoder.matches(adminParameter.getPassword(), adminVo.getPassword())) {
            return AjaxResult.error("原密码错误");
        }
        if (!adminParameter.getNewpassword().equals(adminParameter.getCheckpass())) {
            return AjaxResult.error("两次输入的密码不一致");
        }
        Admin admin = EntityUtils.toObj(adminVo, Admin::new);
        admin.setPassword(passwordEncoder.encode(adminParameter.getNewpassword()));

        int i = adminMapper.updateById(admin);
        if (i > 0) {
            return AjaxResult.success("修改密码成功", null);
        } else {
            return AjaxResult.error("修改密码失败");
        }
    }

    @Override
    public AjaxResult allAdmins(Admin admin) {
        List<Admin> admins = null;
        if (StringUtils.isNotBlank(admin.getName())) {
            admins = adminMapper.selectList(Wrappers.<Admin>lambdaQuery().like(Admin::getName, admin.getName()));
        } else {
            admins = adminMapper.selectList(null);
        }
        List<AdminVo> adminVos = EntityUtils.toList(admins, AdminVo::new);
        extracted(adminVos);
        return AjaxResult.success("获取管理员列表成功", adminVos);
    }

    @Override
    public boolean upload(MultipartFile file) throws IOException {
        AdminVo adminVo = (AdminVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String upload = aliOSSUtils.upload(file);
        if (StringUtils.isEmpty(upload)) {
            return false;
        }
        Admin admin = adminVo;
        admin.setUserface(upload);
        int i = adminMapper.updateById(admin);
        if (i < 0) {
            return false;
        }
        adminVo.setUserface(upload);
        redisUtil.set("security:" + admin.getUsername(), adminVo);
        return true;
    }


    private void extracted(List<AdminVo> adminVos) {
        for (AdminVo adminVo : adminVos) {
            Integer id = adminVo.getId();
            List<AdminRole> adminRoles = adminRoleMapper.selectList(Wrappers.<AdminRole>lambdaQuery().eq(AdminRole::getAdminid, id));
            Set<Integer> roleIds = EntityUtils.toSet(adminRoles, AdminRole::getRid);
            List<Role> roleList = roleMapper.selectBatchIds(roleIds);
            Set<Role> roles = EntityUtils.toSet(roleList, Role::new);
            adminVo.setRoles(roles);
        }
    }
}
