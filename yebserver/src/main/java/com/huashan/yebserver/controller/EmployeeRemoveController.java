package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.EmployeeRemove;
import com.huashan.yebserver.service.IEmployeeRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/employee/remove")
@CrossOrigin
public class EmployeeRemoveController{
    @Autowired
    private IEmployeeRemoveService employeeRemoveService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,EmployeeRemove employeeRemove){
        return AjaxResult.success(employeeRemoveService.page(pageEntity.toPage(), Wrappers.lambdaQuery(employeeRemove)));
    }
    @GetMapping("/list")
    public AjaxResult list(EmployeeRemove employeeRemove){
        return AjaxResult.success(employeeRemoveService.list(Wrappers.lambdaQuery(employeeRemove)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody EmployeeRemove employeeRemove) {
        return AjaxResult.success(employeeRemoveService.save(employeeRemove));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody EmployeeRemove employeeRemove) {
        return AjaxResult.success(employeeRemoveService.updateById(employeeRemove));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(employeeRemoveService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(employeeRemoveService.getById(id));
    }
}
