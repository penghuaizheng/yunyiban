package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.EmployeeTrain;
import com.huashan.yebserver.service.IEmployeeTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/employee/train")
@CrossOrigin
public class EmployeeTrainController{
    @Autowired
    private IEmployeeTrainService employeeTrainService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,EmployeeTrain employeeTrain){
        return AjaxResult.success(employeeTrainService.page(pageEntity.toPage(), Wrappers.lambdaQuery(employeeTrain)));
    }
    @GetMapping("/list")
    public AjaxResult list(EmployeeTrain employeeTrain){
        return AjaxResult.success(employeeTrainService.list(Wrappers.lambdaQuery(employeeTrain)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody EmployeeTrain employeeTrain) {
        return AjaxResult.success(employeeTrainService.save(employeeTrain));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody EmployeeTrain employeeTrain) {
        return AjaxResult.success(employeeTrainService.updateById(employeeTrain));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(employeeTrainService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(employeeTrainService.getById(id));
    }
}
