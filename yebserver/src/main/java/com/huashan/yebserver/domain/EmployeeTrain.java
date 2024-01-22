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
@TableName(value = "t_employee_train")
public class EmployeeTrain implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工编号", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "培训日期", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate traindate;
    @ApiModelProperty(value = "培训内容", position = 4)
    private String traincontent;
    @ApiModelProperty(value = "备注", position = 5)
    private String remark;
    
    public EmployeeTrain(EmployeeTrain employeeTrain) {
        if (Objects.nonNull(employeeTrain)) {
            this.id=employeeTrain.id;
            this.eid=employeeTrain.eid;
            this.traindate=employeeTrain.traindate;
            this.traincontent=employeeTrain.traincontent;
            this.remark=employeeTrain.remark;
        }
    }
}
