package com.huashan.yebserver.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xin.altitude.cms.common.entity.AjaxResult;

@RestControllerAdvice
public class SecurityExceptionHandle {
    @ExceptionHandler
    public AjaxResult handleException(Exception e) {
        if (e instanceof UsernameNotFoundException) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getMessage());
    }
}
