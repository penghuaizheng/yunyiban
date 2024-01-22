package com.huashan.yebserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huashan.yebserver.domain.MailLog;
import com.huashan.yebserver.service.IMailLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
@RestController
@RequestMapping("/yebserver/mail/log")
@CrossOrigin
public class MailLogController{
    @Autowired
    private IMailLogService mailLogService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MailLog mailLog){
        return AjaxResult.success(mailLogService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mailLog)));
    }
    @GetMapping("/list")
    public AjaxResult list(MailLog mailLog){
        return AjaxResult.success(mailLogService.list(Wrappers.lambdaQuery(mailLog)));
    }
}
