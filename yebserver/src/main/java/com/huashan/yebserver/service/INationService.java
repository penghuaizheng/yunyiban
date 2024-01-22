package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Nation;
import xin.altitude.cms.common.entity.AjaxResult;

public interface INationService extends IService<Nation>{
    AjaxResult allNations(Nation nation);
}
