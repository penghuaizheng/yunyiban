package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Oplog;
import com.huashan.yebserver.service.IOplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/oplog")
@CrossOrigin
public class OplogController{
    @Autowired
    private IOplogService oplogService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Oplog oplog){
        return AjaxResult.success(oplogService.page(pageEntity.toPage(), Wrappers.lambdaQuery(oplog)));
    }
    @GetMapping("/list")
    public AjaxResult list(Oplog oplog){
        return AjaxResult.success(oplogService.list(Wrappers.lambdaQuery(oplog)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Oplog oplog) {
        return AjaxResult.success(oplogService.save(oplog));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Oplog oplog) {
        return AjaxResult.success(oplogService.updateById(oplog));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(oplogService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(oplogService.getById(id));
    }
}
