package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.PoliticsStatus;
import com.huashan.yebserver.service.IPoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/politics/status")
@CrossOrigin
public class PoliticsStatusController{
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,PoliticsStatus politicsStatus){
        return AjaxResult.success(politicsStatusService.page(pageEntity.toPage(), Wrappers.lambdaQuery(politicsStatus)));
    }
    @GetMapping("/list")
    public AjaxResult list(PoliticsStatus politicsStatus){
        return politicsStatusService.allPoliticsStatus(politicsStatus);
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody PoliticsStatus politicsStatus) {
        return AjaxResult.success(politicsStatusService.save(politicsStatus));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PoliticsStatus politicsStatus) {
        return AjaxResult.success(politicsStatusService.updateById(politicsStatus));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(politicsStatusService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(politicsStatusService.getById(id));
    }
}
