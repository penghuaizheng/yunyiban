package com.huashan.yebserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_salary_adjust")
public class SalaryAdjust{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工ID", position = 2)
    private Integer eid;
    @ApiModelProperty(value = "调薪日期", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate asdate;
    @ApiModelProperty(value = "调前薪资", position = 4)
    private Integer beforesalary;
    @ApiModelProperty(value = "调后薪资", position = 5)
    private Integer aftersalary;
    @ApiModelProperty(value = "调薪原因", position = 6)
    private String reason;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
    
    public SalaryAdjust(SalaryAdjust salaryAdjust) {
        if (Objects.nonNull(salaryAdjust)) {
            this.id=salaryAdjust.id;
            this.eid=salaryAdjust.eid;
            this.asdate=salaryAdjust.asdate;
            this.beforesalary=salaryAdjust.beforesalary;
            this.aftersalary=salaryAdjust.aftersalary;
            this.reason=salaryAdjust.reason;
            this.remark=salaryAdjust.remark;
        }
    }
}
