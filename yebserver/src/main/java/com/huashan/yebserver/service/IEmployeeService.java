package com.huashan.yebserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Employee;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Date;

public interface IEmployeeService extends IService<Employee>{
    AjaxResult pageList(PageEntity pageEntity, Employee employee, String[] beginDateScope);

    AjaxResult add(Employee employee);

    AjaxResult maxWorkId();

    AjaxResult editById(Employee employee);

    AjaxResult deleteBatchByIds(Integer[] ids);

    AjaxResult allEmployees(Employee employee);
}
