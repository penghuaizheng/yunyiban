package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.PoliticsStatus;
import xin.altitude.cms.common.entity.AjaxResult;

public interface IPoliticsStatusService extends IService<PoliticsStatus>{
    AjaxResult allPoliticsStatus(PoliticsStatus politicsStatus);
}
