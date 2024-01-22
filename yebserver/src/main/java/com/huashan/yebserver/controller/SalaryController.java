package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Salary;
import com.huashan.yebserver.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/salary")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Salary salary){
        return salaryService.pageList(pageEntity, salary);
    }
    @GetMapping("/list")
    public AjaxResult list(Salary salary){
        return salaryService.allSalaries(salary);
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Salary salary) {
        return salaryService.add(salary);
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Salary salary) {
        return salaryService.editById(salary);
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return salaryService.deleteBatchByIds(ids);
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(salaryService.getById(id));
    }
}
