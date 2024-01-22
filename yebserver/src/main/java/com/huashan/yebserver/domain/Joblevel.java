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
@TableName(value = "t_joblevel")
@EqualsAndHashCode(callSuper = false,of = "name")
public class Joblevel implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "职称名称", position = 2)
    @Excel(name = "职称名称")
    private String name;
    @ApiModelProperty(value = "职称等级", position = 3)
    @Excel(name = "职称等级")
    private String titlelevel;
    @ApiModelProperty(value = "创建时间", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间",width = 20, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;
    @ApiModelProperty(value = "是否启用", position = 5)
    private Integer enabled;

    public Joblevel(String name) {
        this.name = name;
    }

    public Joblevel(Joblevel joblevel) {
        if (Objects.nonNull(joblevel)) {
            this.id=joblevel.id;
            this.name=joblevel.name;
            this.titlelevel=joblevel.titlelevel;
            this.createdate=joblevel.createdate;
            this.enabled=joblevel.enabled;
        }
    }
}
