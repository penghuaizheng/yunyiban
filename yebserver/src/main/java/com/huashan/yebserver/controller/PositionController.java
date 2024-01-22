package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.Position;
import com.huashan.yebserver.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/yebserver/position")
@CrossOrigin
public class PositionController {
    @Autowired
    private IPositionService positionService;

    /**
     * 获取职位列表
     * @param pageEntity
     * @param position
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页获取职位列表")
    public AjaxResult page(PageEntity pageEntity, Position position) {
        return positionService.pageList(pageEntity, position);
    }
    @GetMapping("/list")
    @ApiOperation(value = "获取职位列表")
    public AjaxResult list(Position position){
        return positionService.allPositions(position);
    }

    /**
     * 添加职位
     * @param position
     * @return
     */
    @PostMapping("/push")
    @ApiOperation(value = "添加职位")
    public AjaxResult add(@RequestBody Position position) {
        return positionService.add(position);
    }

    /**
     * 修改职位
     * @param position
     * @return
     */
    @PutMapping("/edit")
    @ApiOperation(value = "修改职位")
    public AjaxResult edit(@RequestBody Position position) {
        return positionService.editById(position);
    }

    /**
     * 删除职位
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除职位")
    public AjaxResult delete(Integer[] ids) {
        return positionService.deleteBatchByIds(ids);
    }

    /**
     * 获取职位详情
     * @param id
     * @return
     */
    @GetMapping(value = "/detail")
    public AjaxResult detail(Integer id) {
        return positionService.detail(id);
    }
}
