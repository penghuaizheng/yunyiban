package com.huashan.yebserver.domain.vo;

import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.huashan.yebserver.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo extends Employee {
    @ExcelEntity(name = "部门")
    private Department department;
    @ExcelEntity(name = "民族")
    private Nation nation;
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;
    @ExcelEntity(name = "职位")
    private Position position;
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;
    @ExcelEntity(name = "工资账套")
    private Salary salary;

    public EmployeeVo(Employee employee) {
        super(employee);
    }
}
