package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.EmployeeEc;
import com.huashan.yebserver.service.IEmployeeEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/employee/ec")
@CrossOrigin
public class EmployeeEcController{
    @Autowired
    private IEmployeeEcService employeeEcService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,EmployeeEc employeeEc){
        return AjaxResult.success(employeeEcService.page(pageEntity.toPage(), Wrappers.lambdaQuery(employeeEc)));
    }
    @GetMapping("/list")
    public AjaxResult list(EmployeeEc employeeEc){
        return AjaxResult.success(employeeEcService.list(Wrappers.lambdaQuery(employeeEc)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody EmployeeEc employeeEc) {
        return AjaxResult.success(employeeEcService.save(employeeEc));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody EmployeeEc employeeEc) {
        return AjaxResult.success(employeeEcService.updateById(employeeEc));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(employeeEcService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(employeeEcService.getById(id));
    }
}
