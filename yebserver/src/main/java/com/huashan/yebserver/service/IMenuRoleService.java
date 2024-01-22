package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.MenuRole;
import com.huashan.yebserver.domain.vo.MenuRoleVo;
import xin.altitude.cms.common.entity.AjaxResult;

public interface IMenuRoleService                extends IService<MenuRole>    {
    AjaxResult editByRoleId(MenuRoleVo menuRoleVo);
}
