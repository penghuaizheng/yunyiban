package com.huashan.yebserver.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
@TableName(value = "t_salary")
public class Salary{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id", position = 1)
    private Integer id;
    @ApiModelProperty(value = "基本工资", position = 2)
    @Excel(name = "基本工资")
    private Integer basicsalary;
    @ApiModelProperty(value = "奖金", position = 3)
    @Excel(name = "奖金")
    private Integer bonus;
    @ApiModelProperty(value = "午餐补助", position = 4)
    @Excel(name = "午餐补助")
    private Integer lunchsalary;
    @ApiModelProperty(value = "交通补助", position = 5)
    @Excel(name = "交通补助")
    private Integer trafficsalary;
    @ApiModelProperty(value = "应发工资", position = 6)
    @Excel(name = "应发工资")
    private Integer allsalary;
    @ApiModelProperty(value = "养老金基数", position = 7)
    private Integer pensionbase;
    @ApiModelProperty(value = "养老金比率", position = 8)
    private Float pensionper;
    @ApiModelProperty(value = "启用时间", position = 9)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "启用时间",width = 20, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;
    @ApiModelProperty(value = "医疗基数", position = 10)
    private Integer medicalbase;
    @ApiModelProperty(value = "医疗保险比率", position = 11)
    private Float medicalper;
    @ApiModelProperty(value = "公积金基数", position = 12)
    private Integer accumulationfundbase;
    @ApiModelProperty(value = "公积金比率", position = 13)
    private Float accumulationfundper;
    @ApiModelProperty(value = "名称", position = 14)
    @Excel(name = "名称")
    private String name;

    public Salary(String name) {
        this.name = name;
    }

    public Salary(Salary salary) {
        if (Objects.nonNull(salary)) {
            this.id=salary.id;
            this.basicsalary=salary.basicsalary;
            this.bonus=salary.bonus;
            this.lunchsalary=salary.lunchsalary;
            this.trafficsalary=salary.trafficsalary;
            this.allsalary=salary.allsalary;
            this.pensionbase=salary.pensionbase;
            this.pensionper=salary.pensionper;
            this.createdate=salary.createdate;
            this.medicalbase=salary.medicalbase;
            this.medicalper=salary.medicalper;
            this.accumulationfundbase=salary.accumulationfundbase;
            this.accumulationfundper=salary.accumulationfundper;
            this.name=salary.name;
        }
    }
}
