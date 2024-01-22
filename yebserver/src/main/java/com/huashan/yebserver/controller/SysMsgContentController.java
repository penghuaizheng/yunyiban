package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.SysMsgContent;
import com.huashan.yebserver.service.ISysMsgContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/sys/msg/content")
public class SysMsgContentController {
    @Autowired
    private ISysMsgContentService sysMsgContentService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,SysMsgContent sysMsgContent){
        return AjaxResult.success(sysMsgContentService.page(pageEntity.toPage(), Wrappers.lambdaQuery(sysMsgContent)));
    }
    @GetMapping("/list")
    public AjaxResult list(SysMsgContent sysMsgContent){
        return AjaxResult.success(sysMsgContentService.list(Wrappers.lambdaQuery(sysMsgContent)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody SysMsgContent sysMsgContent) {
        return AjaxResult.success(sysMsgContentService.save(sysMsgContent));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SysMsgContent sysMsgContent) {
        return AjaxResult.success(sysMsgContentService.updateById(sysMsgContent));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(sysMsgContentService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(sysMsgContentService.getById(id));
    }
}
