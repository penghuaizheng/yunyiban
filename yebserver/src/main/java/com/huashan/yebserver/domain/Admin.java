package com.huashan.yebserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "姓名", position = 2)
    private String name;
    @ApiModelProperty(value = "手机号码", position = 3)
    private String phone;
    @ApiModelProperty(value = "住宅电话", position = 4)
    private String telephone;
    @ApiModelProperty(value = "联系地址", position = 5)
    private String address;
    @ApiModelProperty(value = "是否启用", position = 6)
    private Integer enable;
    @ApiModelProperty(value = "用户名", position = 7)
    private String username;
    @ApiModelProperty(value = "密码", position = 8)
    private String password;
    @ApiModelProperty(value = "用户头像", position = 9)
    private String userface;
    @ApiModelProperty(value = "备注", position = 10)
    private String remark;

    public Admin(Admin admin) {
        if (Objects.nonNull(admin)) {
            this.id = admin.id;
            this.name = admin.name;
            this.phone = admin.phone;
            this.telephone = admin.telephone;
            this.address = admin.address;
            this.enable = admin.enable;
            this.username = admin.username;
            this.password = admin.password;
            this.userface = admin.userface;
            this.remark = admin.remark;
        }
    }

}
