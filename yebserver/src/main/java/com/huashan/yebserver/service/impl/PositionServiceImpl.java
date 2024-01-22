package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Position;
import com.huashan.yebserver.mapper.PositionMapper;
import com.huashan.yebserver.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;


    @Override
    public AjaxResult pageList(PageEntity pageEntity, Position position) {
        IPage<Position> page = pageEntity.toPage();
        if (StringUtils.isNotBlank(position.getName())) {
            page = positionMapper.selectPage(page, Wrappers.<Position>lambdaQuery().like(Position::getName, position.getName()));
        } else {
            page = positionMapper.selectPage(page, null);
        }
        List<Position> positions = page.getRecords();
        long total = page.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("positions", positions);
        map.put("total", total);
        return AjaxResult.success("获取职位成功", map);
    }

    @Override
    public AjaxResult add(Position position) {
        if (positionMapper.selectOne(Wrappers.<Position>lambdaQuery().eq(Position::getName, position.getName())) != null) {
            return AjaxResult.error("该职位已存在");
        }
        int insert = positionMapper.insert(position);
        if (insert > 0) {
            return AjaxResult.success("添加职位成功", null);
        } else {
            return AjaxResult.error("添加职位失败");
        }
    }

    @Override
    public AjaxResult editById(Position position) {
        int i = positionMapper.updateById(position);
        if (i > 0) {
            return AjaxResult.success("修改职位成功", null);
        } else {
            return AjaxResult.error("修改职位失败");
        }
    }

    @Override
    public AjaxResult deleteBatchByIds(Integer[] ids) {
        int i = positionMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除职位成功", null);
        } else {
            return AjaxResult.error("删除职位失败");
        }
    }

    @Override
    public AjaxResult detail(Integer id) {
        Position position = positionMapper.selectById(id);
        if (position != null) {
            return AjaxResult.success("获取职位详情成功", position);
        } else {
            return AjaxResult.error("获取职位详情成功");
        }
    }

    @Override
    public AjaxResult allPositions(Position position) {
        if (StringUtils.isNotBlank(position.getName())) {
            List<Position> positions = positionMapper.selectList(Wrappers.<Position>lambdaQuery().like(Position::getName, position.getName()));
            return AjaxResult.success("获取职位列表成功", positions);
        } else {
            List<Position> positions = positionMapper.selectList(null);
            return AjaxResult.success("获取职位列表成功", positions);
        }
    }
}
