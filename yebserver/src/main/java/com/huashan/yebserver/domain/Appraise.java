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
@TableName(value = "t_appraise")
public class Appraise implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工id", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "考评日期", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appdate;
    @ApiModelProperty(value = "考评结果", position = 4)
    private String appresult;
    @ApiModelProperty(value = "考评内容", position = 5)
    private String appcontent;
    @ApiModelProperty(value = "备注", position = 6)
    private String remark;
    
    public Appraise(Appraise appraise) {
        if (Objects.nonNull(appraise)) {
            this.id=appraise.id;
            this.eid=appraise.eid;
            this.appdate=appraise.appdate;
            this.appresult=appraise.appresult;
            this.appcontent=appraise.appcontent;
            this.remark=appraise.remark;
        }
    }
}
