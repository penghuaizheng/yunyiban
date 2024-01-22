package com.huashan.yebserver.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_menu")
public class Menu implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "url", position = 2)
    private String url;
    @ApiModelProperty(value = "path", position = 3)
    private String path;
    @ApiModelProperty(value = "组件", position = 4)
    private String component;
    @ApiModelProperty(value = "菜单名", position = 5)
    private String name;
    @ApiModelProperty(value = "图标", position = 6)
    private String iconcls;
    @ApiModelProperty(value = "是否保持激活", position = 7)
    private Integer keepalive;
    @ApiModelProperty(value = "是否要求权限", position = 8)
    private Integer requireauth;
    @ApiModelProperty(value = "父id", position = 9)
    private Integer parentid;
    @ApiModelProperty(value = "是否启用", position = 10)
    private Integer enabled;
    
    public Menu(Menu menu) {
        if (Objects.nonNull(menu)) {
            this.id=menu.id;
            this.url=menu.url;
            this.path=menu.path;
            this.component=menu.component;
            this.name=menu.name;
            this.iconcls=menu.iconcls;
            this.keepalive=menu.keepalive;
            this.requireauth=menu.requireauth;
            this.parentid=menu.parentid;
            this.enabled=menu.enabled;
        }
    }
}
