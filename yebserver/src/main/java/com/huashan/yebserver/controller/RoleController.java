package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Role;
import com.huashan.yebserver.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/role")
@Api(tags = "RoleController")
@CrossOrigin
public class RoleController {
    @Autowired
    private IRoleService roleService;

    /**
     * 获取角色列表
     *
     * @param pageEntity
     * @param role
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "获取角色列表")
    public AjaxResult page(PageEntity pageEntity, Role role) {
        return roleService.pageList(pageEntity, role);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping("/push")
    @ApiOperation(value = "添加角色")
    public AjaxResult add(@RequestBody Role role) {
        return roleService.add(role);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PutMapping("/edit")
    @ApiOperation(value = "修改角色")
    public AjaxResult edit(@RequestBody Role role) {
        return roleService.editById(role);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除角色")
    public AjaxResult delete(Integer[] ids) {
        return roleService.deleteBatchIds(ids);
    }

    /**
     * 获取角色详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/detail")
    @ApiOperation(value = "获取角色详情")
    public AjaxResult detail(Integer id) {
        return roleService.detail(id);
    }

    @GetMapping(value = "/getPermissionByRoleId")
    @ApiOperation(value = "获取角色权限")
    public AjaxResult getPermissionByRoleId(Integer id) {
        return roleService.getPermissionByRoleId(id);
    }
}
