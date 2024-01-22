package com.huashan.yebserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    @RequestMapping("/hello")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String hello() {
        return "hello";
    }
}
