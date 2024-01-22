package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Department;
import com.huashan.yebserver.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/department")
@Api(tags = "DepartmentController")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @GetMapping("/page")
    @ApiOperation(value = "分页获取部门列表")
    public AjaxResult page(PageEntity pageEntity,Department department){
        return departmentService.pageList(pageEntity, department);
    }
    @GetMapping("/list")
    @ApiOperation(value = "获取部门列表")
    public AjaxResult list(Department department){
        return departmentService.allDepartments(department);
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Department department) {
        return departmentService.add(department);
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Department department) {
        return AjaxResult.success(departmentService.updateById(department));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return departmentService.deleteBatchByIds(ids);
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(departmentService.getById(id));
    }
}
