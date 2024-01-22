package com.huashan.yebserver.domain;
import cn.afterturn.easypoi.excel.annotation.Excel;
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

import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_department")
@EqualsAndHashCode(callSuper = false,of = "name")
public class Department implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "部门名称", position = 2)
    @Excel(name = "部门名称")
    private String name;
    @ApiModelProperty(value = "父id", position = 3)
    private Integer parentid;
    @ApiModelProperty(value = "路径", position = 4)
    private String deppath;
    @ApiModelProperty(value = "是否启用", position = 5)
    private Integer enabled;
    @ApiModelProperty(value = "是否上级", position = 6)
    private Integer isparent;

    public Department(String name) {
        this.name = name;
    }

    public Department(Department department) {
        if (Objects.nonNull(department)) {
            this.id=department.id;
            this.name=department.name;
            this.parentid=department.parentid;
            this.deppath=department.deppath;
            this.enabled=department.enabled;
            this.isparent=department.isparent;
        }
    }
}
