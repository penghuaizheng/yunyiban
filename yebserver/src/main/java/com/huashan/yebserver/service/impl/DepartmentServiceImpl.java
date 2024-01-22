package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Department;
import com.huashan.yebserver.domain.Employee;
import com.huashan.yebserver.domain.Position;
import com.huashan.yebserver.domain.vo.DepartmentVo;
import com.huashan.yebserver.mapper.DepartmentMapper;
import com.huashan.yebserver.mapper.EmployeeMapper;
import com.huashan.yebserver.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Department department) {
        IPage<Department> page = pageEntity.toPage();
        Map<String, Object> map = new HashMap<>();
        department.setParentid(-1);
        if (StringUtils.isNotBlank(department.getName())) {
            page = departmentMapper.selectPage(page, Wrappers.<Department>lambdaQuery().like(Department::getName, department.getName()));
        } else {
            page = departmentMapper.selectPage(page, Wrappers.<Department>lambdaQuery().eq(Department::getParentid, department.getParentid()));
        }
        List<Department> departments = page.getRecords();
        long total = page.getTotal();
        List<DepartmentVo> departmentVos = EntityUtils.toList(departments, DepartmentVo::new);
        // 在原有的代码中调用这个方法
        if (!departmentVos.isEmpty()) {
            for (DepartmentVo departmentVo : departmentVos) {
                setChildren(departmentVo);
            }
        }
        map.put("total", total);
        map.put("departmentVos", departmentVos);
        return AjaxResult.success("获取部门列表成功", map);

    }

    @Override
    public AjaxResult add(Department department) {
        if (departmentMapper.selectOne(Wrappers.<Department>lambdaQuery().eq(Department::getName, department.getName())) != null) {
            return AjaxResult.error("该职位已存在");
        }
        int insert = departmentMapper.insert(department);
        if (insert > 0) {
            Integer parentid = department.getParentid();
            Department parent = departmentMapper.selectOne(Wrappers.<Department>lambdaQuery().eq(Department::getId, parentid));
            if (parent != null) {
                parent.setIsparent(1);
                departmentMapper.updateById(parent);
                String depPath = parent.getDeppath() + "," + department.getId();
                department.setDeppath(depPath);
                departmentMapper.updateById(department);
                return AjaxResult.success("添加部门成功", null);
            }
            return AjaxResult.success("添加部门成功", null);
        }
        return AjaxResult.error("添加部门失败");
    }

    @Override
    public AjaxResult deleteBatchByIds(Integer[] ids) {
        for (Integer id : ids) {
            Department department = departmentMapper.selectOne(Wrappers.<Department>lambdaQuery().eq(Department::getId, id));
            if (department.getIsparent() == 1) {
                return AjaxResult.error("该部门下有子部门，不能删除");
            }
            if (employeeMapper.selectCount(Wrappers.lambdaQuery(Employee.class).eq(Employee::getDepartmentid, id)) > 0) {
                return AjaxResult.error("该部门下有员工，不能删除");
            }
            int i = departmentMapper.deleteById(id);
            if (i > 0) {
                Department parent = departmentMapper.selectOne(Wrappers.<Department>lambdaQuery().eq(Department::getId, department.getParentid()));
                if (parent != null) {
                    List<Department> list = departmentMapper.selectList(Wrappers.<Department>lambdaQuery().eq(Department::getParentid, parent.getId()));
                    if (list.isEmpty()) {
                        parent.setIsparent(0);
                        departmentMapper.updateById(parent);
                    }
                }
            }

        }
        return AjaxResult.success("删除部门成功", null);
    }

    @Override
    public AjaxResult allDepartments(Department department) {
        if (StringUtils.isNotBlank(department.getName())) {
            List<Department> departments = departmentMapper.selectList(Wrappers.<Department>lambdaQuery().like(Department::getName, department.getName()));
            return AjaxResult.success("获取部门列表成功", departments);
        } else {
            List<Department> departments = departmentMapper.selectList(null);
            return AjaxResult.success("获取部门列表成功", departments);
        }
    }


    private void setChildren(DepartmentVo departmentVo) {
        List<Department> list = departmentMapper.selectList(Wrappers.<Department>lambdaQuery().eq(Department::getParentid, departmentVo.getId()));
        List<DepartmentVo> vos = EntityUtils.toList(list, DepartmentVo::new);
        departmentVo.setChildren(vos);
        if (!vos.isEmpty()) {
            for (DepartmentVo vo : vos) {
                setChildren(vo);
            }
        }
    }
}
