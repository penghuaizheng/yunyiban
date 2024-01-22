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
@TableName(value = "t_menu_role")
public class MenuRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "菜单id", position = 2)
    private Integer mid;
    @ApiModelProperty(value = "权限id", position = 3)
    private Integer rid;

    public MenuRole(MenuRole menuRole) {
        if (Objects.nonNull(menuRole)) {
            this.id = menuRole.id;
            this.mid = menuRole.mid;
            this.rid = menuRole.rid;
        }
    }
}
