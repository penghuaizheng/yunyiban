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
@TableName(value = "t_employee_remove")
public class EmployeeRemove implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工id", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "调动后部门", position = 3)
    private Integer afterdepid;
    @ApiModelProperty(value = "调动后职位", position = 4)
    private Integer afterjobid;
    @ApiModelProperty(value = "调动日期", position = 5)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate removedate;
    @ApiModelProperty(value = "调动原因", position = 6)
    private String reason;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
    
    public EmployeeRemove(EmployeeRemove employeeRemove) {
        if (Objects.nonNull(employeeRemove)) {
            this.id=employeeRemove.id;
            this.eid=employeeRemove.eid;
            this.afterdepid=employeeRemove.afterdepid;
            this.afterjobid=employeeRemove.afterjobid;
            this.removedate=employeeRemove.removedate;
            this.reason=employeeRemove.reason;
            this.remark=employeeRemove.remark;
        }
    }
}
