package com.huashan.yebserver.config.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huashan.yebserver.domain.Admin;
import com.huashan.yebserver.domain.AdminRole;
import com.huashan.yebserver.domain.Role;
import com.huashan.yebserver.domain.vo.AdminVo;
import com.huashan.yebserver.mapper.AdminMapper;
import com.huashan.yebserver.mapper.AdminRoleMapper;
import com.huashan.yebserver.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", s);
        Admin admin = adminMapper.selectOne(queryWrapper);
        log.info("admin:{}", admin);
        if (ObjectUtils.isEmpty(admin)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        AdminVo adminVo = EntityUtils.toObj(admin, AdminVo::new);
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("adminId", admin.getId());
        List<AdminRole> adminRoles = adminRoleMapper.selectList(adminRoleQueryWrapper);
        Set<Role> roles = new LinkedHashSet<>();
        adminRoles.forEach(adminRole -> {
            Role role = roleMapper.selectById(adminRole.getRid());
            roles.add(role);
        });
        adminVo.setRoles(roles);
        return adminVo;
    }
}
