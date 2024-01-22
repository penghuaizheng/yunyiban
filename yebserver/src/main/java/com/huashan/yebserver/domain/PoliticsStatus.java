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
@TableName(value = "t_politics_status")
@EqualsAndHashCode(callSuper = false,of = "name")
public class PoliticsStatus implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "政治面貌", position = 2)
    @Excel(name = "政治面貌")
    private String name;

    public PoliticsStatus(String name) {
        this.name = name;
    }

    public PoliticsStatus(PoliticsStatus politicsStatus) {
        if (Objects.nonNull(politicsStatus)) {
            this.id=politicsStatus.id;
            this.name=politicsStatus.name;
        }
    }
}
