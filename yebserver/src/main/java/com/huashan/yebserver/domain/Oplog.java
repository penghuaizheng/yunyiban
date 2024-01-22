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
@TableName(value = "t_oplog")
public class Oplog implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "添加日期", position = 2)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate adddate;
    @ApiModelProperty(value = "操作内容", position = 3)
    private String operate;
    @ApiModelProperty(value = "操作员ID", position = 4)
    private Integer adminid;
    
    public Oplog(Oplog oplog) {
        if (Objects.nonNull(oplog)) {
            this.id=oplog.id;
            this.adddate=oplog.adddate;
            this.operate=oplog.operate;
            this.adminid=oplog.adminid;
        }
    }
}
