package com.huashan.yebserver.controller;

import com.huashan.yebserver.domain.Admin;
import com.huashan.yebserver.domain.Position;
import com.huashan.yebserver.domain.vo.AdminParameter;
import com.huashan.yebserver.domain.vo.AdminVo;
import com.huashan.yebserver.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "LoginController")
@RequestMapping("/yebserver/admin")
@CrossOrigin
public class LoginController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    @ApiOperation(value = "登录之后返回token")
    public AjaxResult login(@RequestBody AdminParameter adminParameter, HttpServletRequest request) {
        return adminService.login(adminParameter, request);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public AjaxResult logout() {
        return adminService.logout();
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public AjaxResult info() {
        return adminService.getInfo();
    }

    @GetMapping("/menus")
    @ApiOperation(value = "获取菜单列表")
    public AjaxResult menus() {
        return adminService.getMenusByAdminId();
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页获取管理员列表")
    public AjaxResult page(PageEntity pageEntity, Admin admin) {
        return adminService.pageList(pageEntity, admin);
    }
    @GetMapping("/list")
    @ApiOperation(value = "获取管理员列表")
    public AjaxResult list(Admin admin) {
        return adminService.allAdmins(admin);
    }
    @PutMapping("/edit")
    @ApiOperation(value = "修改管理员")
    public AjaxResult edit(@RequestBody AdminVo adminVo) {
        return adminService.editById(adminVo);
    }
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除管理员")
    public AjaxResult delete(Integer[] ids) {
        return adminService.deleteBatchByIds(ids);
    }
    @PutMapping("/personaledit")
    @ApiOperation("修改个人信息")
    public AjaxResult edit(@RequestBody Admin admin){
        return adminService.edit(admin);
    }
    @PutMapping("/passwordedit")
    @ApiOperation("修改密码")
    public AjaxResult editPassword(@RequestBody AdminParameter adminParameter){
        return adminService.editPassword(adminParameter);
    }
}
