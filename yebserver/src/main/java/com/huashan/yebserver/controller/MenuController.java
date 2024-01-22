package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Menu;
import com.huashan.yebserver.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/yebserver/menu")
@CrossOrigin
public class MenuController{
    @Autowired
    private IMenuService menuService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Menu menu){
        return AjaxResult.success(menuService.page(pageEntity.toPage(), Wrappers.lambdaQuery(menu)));
    }
    @GetMapping("/list")
    public AjaxResult list(Menu menu){
        return menuService.allMenus(menu);
    }
    @GetMapping("/tree/{rid}")
    public AjaxResult tree(@PathVariable Integer rid){
        return menuService.tree(rid);
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Menu menu) {
        return AjaxResult.success(menuService.save(menu));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Menu menu) {
        return AjaxResult.success(menuService.updateById(menu));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(menuService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(menuService.getById(id));
    }
}
