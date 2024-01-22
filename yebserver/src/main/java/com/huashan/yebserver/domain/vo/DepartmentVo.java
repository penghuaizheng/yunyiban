package com.huashan.yebserver.domain.vo;

import com.huashan.yebserver.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVo extends Department{
    List<DepartmentVo> children;
    public DepartmentVo(Department department) {
        super(department);
    }
}
