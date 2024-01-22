package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Joblevel;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

public interface IJoblevelService extends IService<Joblevel>{
    AjaxResult pageList(PageEntity pageEntity, Joblevel joblevel);

    AjaxResult add(Joblevel joblevel);

    AjaxResult editById(Joblevel joblevel);

    AjaxResult deleteBatchIds(Integer[] ids);

    AjaxResult detail(Integer id);

    AjaxResult allJoblevels(Joblevel joblevel);
}
