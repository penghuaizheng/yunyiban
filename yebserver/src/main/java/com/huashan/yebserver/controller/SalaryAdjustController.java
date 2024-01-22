package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.SalaryAdjust;
import com.huashan.yebserver.service.ISalaryAdjustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/salary/adjust")
public class SalaryAdjustController {
    @Autowired
    private ISalaryAdjustService salaryAdjustService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,SalaryAdjust salaryAdjust){
        return AjaxResult.success(salaryAdjustService.page(pageEntity.toPage(), Wrappers.lambdaQuery(salaryAdjust)));
    }
    @GetMapping("/list")
    public AjaxResult list(SalaryAdjust salaryAdjust){
        return AjaxResult.success(salaryAdjustService.list(Wrappers.lambdaQuery(salaryAdjust)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody SalaryAdjust salaryAdjust) {
        return AjaxResult.success(salaryAdjustService.save(salaryAdjust));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SalaryAdjust salaryAdjust) {
        return AjaxResult.success(salaryAdjustService.updateById(salaryAdjust));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(salaryAdjustService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(salaryAdjustService.getById(id));
    }
}
