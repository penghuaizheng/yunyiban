package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Role;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

public interface IRoleService extends IService<Role>{
    AjaxResult pageList(PageEntity pageEntity, Role role);

    AjaxResult add(Role role);

    AjaxResult editById(Role role);

    AjaxResult deleteBatchIds(Integer[] ids);

    AjaxResult detail(Integer id);

    AjaxResult getPermissionByRoleId(Integer id);
}
