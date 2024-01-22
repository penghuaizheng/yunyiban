package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Nation;
import com.huashan.yebserver.mapper.NationMapper;
import com.huashan.yebserver.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.List;

@Service
public class NationServiceImpl extends ServiceImpl<NationMapper,Nation> implements INationService{
    @Autowired
    private NationMapper nationMapper;
    @Override
    public AjaxResult allNations(Nation nation) {
        if (StringUtils.isEmpty(nation.getName())){
            List<Nation> nations = nationMapper.selectList(null);
            return AjaxResult.success("获取民族列表成功",nations);
        }else {
            List<Nation> nations = nationMapper.selectList(Wrappers.<Nation>lambdaQuery().like(Nation::getName, nation.getName()));
            return AjaxResult.success("获取民族列表成功",nations);
        }
    }
}
