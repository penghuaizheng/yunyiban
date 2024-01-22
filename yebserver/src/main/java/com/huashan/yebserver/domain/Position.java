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
@EqualsAndHashCode(callSuper = false,of = "name")
@TableName(value = "t_position")
public class Position implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "职位", position = 2)
    @Excel(name = "职位")
    private String name;
    @ApiModelProperty(value = "创建时间", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;
    @ApiModelProperty(value = "是否启用", position = 4)
    private Integer enabled;

    public Position(String name) {
        this.name = name;
    }

    public Position(Position position) {
        if (Objects.nonNull(position)) {
            this.id=position.id;
            this.name=position.name;
            this.createdate=position.createdate;
            this.enabled=position.enabled;
        }
    }
}
