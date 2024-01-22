package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Menu;
import com.huashan.yebserver.domain.MenuRole;
import com.huashan.yebserver.domain.Role;
import com.huashan.yebserver.domain.vo.MenuVo;
import com.huashan.yebserver.mapper.MenuMapper;
import com.huashan.yebserver.mapper.MenuRoleMapper;
import com.huashan.yebserver.mapper.RoleMapper;
import com.huashan.yebserver.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Role role) {
        IPage<Role> page = pageEntity.toPage();
        if (StringUtils.isNotBlank(role.getNamezh())) {
            page = roleMapper.selectPage(page, Wrappers.<Role>lambdaQuery().like(Role::getNamezh, role.getNamezh()));
        } else {
            page = roleMapper.selectPage(page, null);
        }
        List<Role> roles = page.getRecords();
        long total = page.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("total", total);
        return AjaxResult.success("获取角色成功", map);
    }

    @Override
    public AjaxResult add(Role role) {
        Role role1 = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getNamezh, role.getNamezh()));
        if (role1 != null) {
            return AjaxResult.error("该角色已存在");
        }
        int insert = roleMapper.insert(role);
        if (insert > 0) {
            return AjaxResult.success("添加角色成功", null);

        }
        return AjaxResult.error("添加角色失败");
    }

    @Override
    public AjaxResult editById(Role role) {
        int i = roleMapper.updateById(role);
        if (i > 0) {
            return AjaxResult.success("修改角色成功", null);
        } else {
            return AjaxResult.error("修改角色失败");
        }
    }

    @Override
    public AjaxResult deleteBatchIds(Integer[] ids) {
        int delete = menuRoleMapper.delete(Wrappers.<MenuRole>lambdaQuery().in(MenuRole::getRid, List.of(ids)));
        int i = roleMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除角色成功", null);
        }

        return AjaxResult.error("删除角色失败");
    }

    @Override
    public AjaxResult detail(Integer id) {
        Role role = roleMapper.selectById(id);
        if (role != null) {
            return AjaxResult.success("获取角色详情成功", role);
        } else {
            return AjaxResult.error("获取角色详情失败");
        }
    }

    @Override
    public AjaxResult getPermissionByRoleId(Integer id) {
        List<MenuRole> menuRoles = menuRoleMapper.selectList(Wrappers.<MenuRole>lambdaQuery().eq(MenuRole::getRid, id));
        List<Integer> menuIds = EntityUtils.toList(menuRoles, MenuRole::getMid);
        List<Menu> childrenMenus = menuMapper.selectBatchIds(menuIds);
        List<Integer> parentIds = EntityUtils.toList(childrenMenus, Menu::getParentid);
        List<Menu> parentMenus = menuMapper.selectBatchIds(parentIds);
        List<MenuVo> parentMenuVos = EntityUtils.toList(parentMenus, MenuVo::new);
        for (MenuVo parentMenuVo : parentMenuVos) {
            Integer mid = parentMenuVo.getId();
            List<Menu> menus = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class).eq(Menu::getParentid, mid));
            List<MenuVo> menuVos = EntityUtils.toList(menus, MenuVo::new);
            parentMenuVo.setChildren(menuVos);
        }
        return AjaxResult.success("获取权限成功", parentMenuVos);
    }
}
