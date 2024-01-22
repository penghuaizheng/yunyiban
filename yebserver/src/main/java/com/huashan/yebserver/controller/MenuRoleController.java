package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.MenuRole;
import com.huashan.yebserver.domain.vo.MenuRoleVo;
import com.huashan.yebserver.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/menu/role")
@CrossOrigin
public class MenuRoleController{
    @Autowired
    private IMenuRoleService menuRoleService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MenuRole menuRole){
        return AjaxResult.success(menuRoleService.page(pageEntity.toPage(), Wrappers.lambdaQuery(menuRole)));
    }
    @GetMapping("/list")
    public AjaxResult list(MenuRole menuRole){
        return AjaxResult.success(menuRoleService.list(Wrappers.lambdaQuery(menuRole)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MenuRole menuRole) {
        return AjaxResult.success(menuRoleService.save(menuRole));
    }
    @PutMapping("/edit")
    public AjaxResult edit(MenuRoleVo menuRoleVo) {
        return menuRoleService.editByRoleId(menuRoleVo);
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(menuRoleService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(menuRoleService.getById(id));
    }
}
