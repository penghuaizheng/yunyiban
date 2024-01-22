package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Menu;
import xin.altitude.cms.common.entity.AjaxResult;

public interface IMenuService extends IService<Menu>{
    AjaxResult allMenus(Menu menu);

    AjaxResult tree(Integer rid);
}
