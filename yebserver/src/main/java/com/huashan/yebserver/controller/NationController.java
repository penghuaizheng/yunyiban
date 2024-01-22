package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Nation;
import com.huashan.yebserver.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/nation")
@CrossOrigin
public class NationController{
    @Autowired
    private INationService nationService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Nation nation){
        return AjaxResult.success(nationService.page(pageEntity.toPage(), Wrappers.lambdaQuery(nation)));
    }
    @GetMapping("/list")
    public AjaxResult list(Nation nation){
        return nationService.allNations(nation);
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Nation nation) {
        return AjaxResult.success(nationService.save(nation));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Nation nation) {
        return AjaxResult.success(nationService.updateById(nation));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(nationService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(nationService.getById(id));
    }
}
