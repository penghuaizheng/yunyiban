package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.PoliticsStatus;
import com.huashan.yebserver.mapper.PoliticsStatusMapper;
import com.huashan.yebserver.service.IPoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.List;

@Service
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper,PoliticsStatus> implements IPoliticsStatusService{
    @Autowired
    private PoliticsStatusMapper politicsStatusMapper;
    @Override
    public AjaxResult allPoliticsStatus(PoliticsStatus politicsStatus) {
        if (StringUtils.isEmpty(politicsStatus.getName())){
            List<PoliticsStatus> politicsStatuses = politicsStatusMapper.selectList(null);
            return AjaxResult.success("获取政治面貌列表成功",politicsStatuses);
        }else {
            List<PoliticsStatus> politicsStatuses = politicsStatusMapper.selectList(Wrappers.<PoliticsStatus>lambdaQuery().like(PoliticsStatus::getName, politicsStatus.getName()));
            return AjaxResult.success("获取政治面貌列表成功",politicsStatuses);
        }
    }
}
