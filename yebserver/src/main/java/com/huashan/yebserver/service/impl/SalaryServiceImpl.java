package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Salary;
import com.huashan.yebserver.mapper.SalaryMapper;
import com.huashan.yebserver.service.ISalaryService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Salary salary) {
        IPage<Salary> page = pageEntity.toPage();
        if (!StringUtils.isEmpty(salary.getName())) {
            page = salaryMapper.selectPage(page, Wrappers.lambdaQuery(salary).like(Salary::getName, salary.getName()));
        } else {
            page = salaryMapper.selectPage(page, null);
        }
        List<Salary> salaries = page.getRecords();
        long total = page.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("salaries", salaries);
        map.put("total", total);
        return AjaxResult.success("获取工资列表成功", map);
    }

    @Override
    public AjaxResult allSalaries(Salary salary) {
        if (!StringUtils.isEmpty(salary.getName())) {
            List<Salary> salaries = salaryMapper.selectList(Wrappers.lambdaQuery(salary).like(Salary::getName, salary.getName()));
            return AjaxResult.success("获取工资列表成功", salaries);
        } else {
            List<Salary> salaries = salaryMapper.selectList(null);
            return AjaxResult.success("获取工资列表成功", salaries);
        }
    }

    @Override
    public AjaxResult add(Salary salary) {
        if (salaryMapper.selectOne(Wrappers.lambdaQuery(salary).eq(Salary::getName, salary.getName())) != null) {
            return AjaxResult.error("该工资已存在");
        }
        salary.setAllsalary((int) (salary.getBasicsalary() + salary.getBonus() + salary.getLunchsalary() + salary.getTrafficsalary() +salary.getPensionbase()*salary.getPensionper()+salary.getMedicalbase()*salary.getMedicalper()+salary.getAccumulationfundbase()*salary.getAccumulationfundper()));
        int insert = salaryMapper.insert(salary);
        if (insert > 0) {
            return AjaxResult.success("添加工资成功", null);
        } else {
            return AjaxResult.error("添加工资失败");
        }
    }

    @Override
    public AjaxResult editById(Salary salary) {
        int update = salaryMapper.updateById(salary);
        if (update > 0) {
            return AjaxResult.success("修改工资成功", null);
        } else {
            return AjaxResult.error("修改工资失败");
        }
    }

    @Override
    public AjaxResult deleteBatchByIds(Integer[] ids) {
        int i = salaryMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除工资成功", null);
        } else {
            return AjaxResult.error("删除工资失败");
        }
    }
}
