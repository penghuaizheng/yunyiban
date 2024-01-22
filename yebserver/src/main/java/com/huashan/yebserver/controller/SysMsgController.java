package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.SysMsg;
import com.huashan.yebserver.service.ISysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/sys/msg")
public class SysMsgController {
    @Autowired
    private ISysMsgService sysMsgService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,SysMsg sysMsg){
        return AjaxResult.success(sysMsgService.page(pageEntity.toPage(), Wrappers.lambdaQuery(sysMsg)));
    }
    @GetMapping("/list")
    public AjaxResult list(SysMsg sysMsg){
        return AjaxResult.success(sysMsgService.list(Wrappers.lambdaQuery(sysMsg)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody SysMsg sysMsg) {
        return AjaxResult.success(sysMsgService.save(sysMsg));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SysMsg sysMsg) {
        return AjaxResult.success(sysMsgService.updateById(sysMsg));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(sysMsgService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(sysMsgService.getById(id));
    }
}
