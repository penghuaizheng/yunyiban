package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Joblevel;
import com.huashan.yebserver.service.IJoblevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;

@RestController
@RequestMapping("/yebserver/joblevel")
@Api(tags = "JoblevelController")
@CrossOrigin
public class JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;

    /**
     * 获取职称列表
     * @param pageEntity
     * @param joblevel
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页获取职称列表")
    public AjaxResult page(PageEntity pageEntity, Joblevel joblevel) {
        return joblevelService.pageList(pageEntity, joblevel);
    }
    @GetMapping("/list")
    @ApiOperation(value = "获取职称列表")
    public AjaxResult list(Joblevel joblevel) {
        return joblevelService.allJoblevels(joblevel);
    }

    /**
     * 添加职称
     * @param joblevel
     * @return
     */
    @PostMapping("/push")
    @ApiOperation(value = "添加职称")
    public AjaxResult add(@RequestBody Joblevel joblevel) {
        return joblevelService.add(joblevel);
    }

    /**
     * 修改职称
     * @param joblevel
     * @return
     */
    @PutMapping("/edit")
    @ApiOperation(value = "修改职称")
    public AjaxResult edit(@RequestBody Joblevel joblevel) {
        return joblevelService.editById(joblevel);
    }

    /**
     * 删除职称
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除职称")
    public AjaxResult delete(Integer[] ids) {
        return joblevelService.deleteBatchIds(ids);
    }

    /**
     * 获取职称详情
     * @param id
     * @return
     */
    @GetMapping(value = "/detail")
    @ApiOperation(value = "获取职称详情")
    public AjaxResult detail(Integer id) {
        return joblevelService.detail(id);
    }
}
