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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_employee")
public class Employee  implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "员工编号", position = 1)
    private Integer id;
    @ApiModelProperty(value = "员工姓名", position = 2)
    @Excel(name = "员工姓名")
    private String name;
    @ApiModelProperty(value = "性别", position = 3)
    @Excel(name = "性别")
    private String gender;
    @ApiModelProperty(value = "出生日期", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate birthday;
    @ApiModelProperty(value = "身份证号", position = 5)
    @Excel(name = "身份证号")
    private String idcard;
    @ApiModelProperty(value = "婚姻状况", position = 6)
    @Excel(name = "婚姻状况")
    private String wedlock;
    @ApiModelProperty(value = "民族", position = 7)
    private Integer nationid;
    @ApiModelProperty(value = "籍贯", position = 8)
    @Excel(name = "籍贯")
    private String nativeplace;
    @ApiModelProperty(value = "政治面貌", position = 9)
    private Integer politicid;
    @ApiModelProperty(value = "邮箱", position = 10)
    @Excel(name = "邮箱")
    private String email;
    @ApiModelProperty(value = "电话号码", position = 11)
    @Excel(name = "电话号码")
    private String phone;
    @ApiModelProperty(value = "联系地址", position = 12)
    @Excel(name = "联系地址")
    private String address;
    @ApiModelProperty(value = "所属部门", position = 13)
    private Integer departmentid;
    @ApiModelProperty(value = "职称ID", position = 14)
    private Integer joblevelid;
    @ApiModelProperty(value = "职位ID", position = 15)
    private Integer posid;
    @ApiModelProperty(value = "聘用形式", position = 16)
    @Excel(name = "聘用形式")
    private String engageform;
    @ApiModelProperty(value = "最高学历", position = 17)
    @Excel(name = "最高学历")
    private String tiptopdegree;
    @ApiModelProperty(value = "所属专业", position = 18)
    @Excel(name = "所属专业")
    private String specialty;
    @ApiModelProperty(value = "毕业院校", position = 19)
    @Excel(name = "毕业院校")
    private String school;
    @ApiModelProperty(value = "入职日期", position = 20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate begindate;
    @ApiModelProperty(value = "在职状态", position = 21)
    @Excel(name = "在职状态")
    private String workstate;
    @ApiModelProperty(value = "工号", position = 22)
    @Excel(name = "工号")
    private String workid;
    @ApiModelProperty(value = "合同期限", position = 23)
    @Excel(name = "合同期限",suffix = "年")
    private Double contractterm;
    @ApiModelProperty(value = "转正日期", position = 24)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转正日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate conversiontime;
    @ApiModelProperty(value = "离职日期", position = 25)
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "离职日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate notworkdate;
    @ApiModelProperty(value = "合同起始日期", position = 26)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同起始日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate begincontract;
    @ApiModelProperty(value = "合同终止日期", position = 27)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同终止日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate endcontract;
    @ApiModelProperty(value = "工龄", position = 28)
//    @Excel(name = "工龄")
    private Integer workage;
    @ApiModelProperty(value = "工资账套ID", position = 29)
    private Integer salaryid;
    
    public Employee(Employee employee) {
        if (Objects.nonNull(employee)) {
            this.id=employee.id;
            this.name=employee.name;
            this.gender=employee.gender;
            this.birthday=employee.birthday;
            this.idcard=employee.idcard;
            this.wedlock=employee.wedlock;
            this.nationid=employee.nationid;
            this.nativeplace=employee.nativeplace;
            this.politicid=employee.politicid;
            this.email=employee.email;
            this.phone=employee.phone;
            this.address=employee.address;
            this.departmentid=employee.departmentid;
            this.joblevelid=employee.joblevelid;
            this.posid=employee.posid;
            this.engageform=employee.engageform;
            this.tiptopdegree=employee.tiptopdegree;
            this.specialty=employee.specialty;
            this.school=employee.school;
            this.begindate=employee.begindate;
            this.workstate=employee.workstate;
            this.workid=employee.workid;
            this.contractterm=employee.contractterm;
            this.conversiontime=employee.conversiontime;
            this.notworkdate=employee.notworkdate;
            this.begincontract=employee.begincontract;
            this.endcontract=employee.endcontract;
            this.workage=employee.workage;
            this.salaryid=employee.salaryid;
        }
    }
}
