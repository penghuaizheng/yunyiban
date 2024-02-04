package com.huashan.yebserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Admin;
import com.huashan.yebserver.domain.vo.AdminParameter;
import com.huashan.yebserver.domain.vo.AdminVo;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

public interface IAdminService extends IService<Admin> {
    AjaxResult login(AdminParameter adminParameter, HttpServletRequest request);

    AjaxResult logout();

    AjaxResult getInfo();

    AjaxResult getMenusByAdminId();

    AjaxResult pageList(PageEntity pageEntity, Admin admin);

    AjaxResult editById(AdminVo adminVo);

    AjaxResult deleteBatchByIds(Integer[] ids);

    AjaxResult edit(Admin admin);

    AjaxResult editPassword(AdminParameter adminParameter);

    AjaxResult allAdmins(Admin admin);

    boolean upload(MultipartFile file) throws IOException;
}
