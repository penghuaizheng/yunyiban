package com.huashan.yebserver.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminParameter {
    private String username;
    private String password;
    private String code;
    private String checkpass;
    private String newpassword;
}
