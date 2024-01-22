package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.MenuRole;
import com.huashan.yebserver.domain.vo.MenuRoleVo;
import com.huashan.yebserver.mapper.MenuRoleMapper;
import com.huashan.yebserver.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.Arrays;
import java.util.Collections;

@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper,MenuRole> implements IMenuRoleService{
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Override
    public AjaxResult editByRoleId(MenuRoleVo menuRoleVo) {
        menuRoleMapper.delete(Wrappers.<MenuRole>lambdaQuery().eq(MenuRole::getRid,menuRoleVo.getRid()));
        if (ObjectUtils.isEmpty(menuRoleVo.getMids())){
            return AjaxResult.success("更新成功",null);
        }
        for (Integer mid : menuRoleVo.getMids()) {
            MenuRole menuRole = new MenuRole();
            menuRole.setMid(mid);
            menuRole.setRid(menuRoleVo.getRid());
            menuRoleMapper.insert(menuRole);
        }
        return AjaxResult.success("更新成功",null);
    }
}
