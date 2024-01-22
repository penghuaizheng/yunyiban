package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Joblevel;
import com.huashan.yebserver.mapper.JoblevelMapper;
import com.huashan.yebserver.service.IJoblevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {
    @Autowired
    private JoblevelMapper joblevelMapper;

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Joblevel joblevel) {
        IPage<Joblevel> page = pageEntity.toPage();
        if (StringUtils.isNotBlank(joblevel.getName()) && StringUtils.isNotBlank(joblevel.getTitlelevel())) {
            page = joblevelMapper.selectPage(page, Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getName, joblevel.getName()).like(Joblevel::getTitlelevel, joblevel.getTitlelevel()));
        } else if (StringUtils.isNotBlank(joblevel.getName())) {
            page = joblevelMapper.selectPage(page, Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getName, joblevel.getName()));
        } else if (StringUtils.isNotBlank(joblevel.getTitlelevel())) {
            page = joblevelMapper.selectPage(page, Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getTitlelevel, joblevel.getTitlelevel()));
        } else {
            page = joblevelMapper.selectPage(page, null);
        }
        List<Joblevel> joblevels = page.getRecords();
        long total = page.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("joblevels", joblevels);
        map.put("total", total);
        return AjaxResult.success("获取职称成功", map);
    }

    @Override
    public AjaxResult add(Joblevel joblevel) {
        Joblevel joblevel1 = joblevelMapper.selectOne(Wrappers.<Joblevel>lambdaQuery().eq(Joblevel::getName, joblevel.getName()));
        if (joblevel1 != null) {
            return AjaxResult.error("该职称已存在");
        }
        int insert = joblevelMapper.insert(joblevel);
        if (insert > 0) {
            return AjaxResult.success("添加职称成功", null);
        } else {
            return AjaxResult.error("添加职称失败");
        }
    }

    @Override
    public AjaxResult editById(Joblevel joblevel) {
        int update = joblevelMapper.updateById(joblevel);
        if (update > 0) {
            return AjaxResult.success("修改职称成功", null);
        } else {
            return AjaxResult.error("修改职称失败");
        }
    }

    @Override
    public AjaxResult deleteBatchIds(Integer[] ids) {
        int i = joblevelMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除职称成功", null);
        } else {
            return AjaxResult.error("删除职称失败");
        }
    }

    @Override
    public AjaxResult detail(Integer id) {
        Joblevel joblevel = joblevelMapper.selectById(id);
        if (joblevel != null) {
            return AjaxResult.success("获取职称成功", joblevel);
        } else {
            return AjaxResult.error("获取职称失败");
        }
    }

    @Override
    public AjaxResult allJoblevels(Joblevel joblevel) {
        if (StringUtils.isNotBlank(joblevel.getName()) && StringUtils.isNotBlank(joblevel.getTitlelevel())) {
            List<Joblevel> joblevels = joblevelMapper.selectList(Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getName, joblevel.getName()).like(Joblevel::getTitlelevel, joblevel.getTitlelevel()));
            return AjaxResult.success("获取职称成功", joblevels);
        } else if (StringUtils.isNotBlank(joblevel.getName())) {
            List<Joblevel> joblevels = joblevelMapper.selectList(Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getName, joblevel.getName()));
            return AjaxResult.success("获取职称成功", joblevels);
        } else if (StringUtils.isNotBlank(joblevel.getTitlelevel())) {
            List<Joblevel> joblevels = joblevelMapper.selectList(Wrappers.<Joblevel>lambdaQuery().like(Joblevel::getTitlelevel, joblevel.getTitlelevel()));
            return AjaxResult.success("获取职称成功", joblevels);
        } else {
            List<Joblevel> joblevels = joblevelMapper.selectList(null);
            return AjaxResult.success("获取职称成功", joblevels);
        }
    }
}
