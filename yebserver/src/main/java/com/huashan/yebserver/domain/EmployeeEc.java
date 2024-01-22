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
@TableName(value = "t_employee_ec")
public class EmployeeEc  implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工编号", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "奖罚日期", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ecdate;
    @ApiModelProperty(value = "奖罚原因", position = 4)
    private String ecreason;
    @ApiModelProperty(value = "奖罚分", position = 5)
    private Integer ecpoint;
    @ApiModelProperty(value = "奖罚类别，0：奖，1：罚", position = 6)
    private Integer ectype;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
    
    public EmployeeEc(EmployeeEc employeeEc) {
        if (Objects.nonNull(employeeEc)) {
            this.id=employeeEc.id;
            this.eid=employeeEc.eid;
            this.ecdate=employeeEc.ecdate;
            this.ecreason=employeeEc.ecreason;
            this.ecpoint=employeeEc.ecpoint;
            this.ectype=employeeEc.ectype;
            this.remark=employeeEc.remark;
        }
    }
}
