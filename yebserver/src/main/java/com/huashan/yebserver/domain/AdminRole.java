package com.huashan.yebserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_admin_role")
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer adminid;
    @ApiModelProperty(value = "权限id", position = 3)
    private Integer rid;

    public AdminRole(AdminRole adminRole) {
        if (Objects.nonNull(adminRole)) {
            this.id = adminRole.id;
            this.adminid = adminRole.adminid;
            this.rid = adminRole.rid;
        }
    }
}
