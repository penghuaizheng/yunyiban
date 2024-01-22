package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Salary;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

public interface ISalaryService extends IService<Salary>{

    AjaxResult pageList(PageEntity pageEntity, Salary salary);

    AjaxResult allSalaries(Salary salary);

    AjaxResult add(Salary salary);

    AjaxResult editById(Salary salary);

    AjaxResult deleteBatchByIds(Integer[] ids);
}
