package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Department;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

public interface IDepartmentService extends IService<Department>{
    AjaxResult pageList(PageEntity pageEntity, Department department);

    AjaxResult add(Department department);

    AjaxResult deleteBatchByIds(Integer[] ids);

    AjaxResult allDepartments(Department department);
}
