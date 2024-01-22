package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Employee;
import com.huashan.yebserver.domain.vo.EmployeeVo;
import com.huashan.yebserver.mapper.*;
import com.huashan.yebserver.service.IEmployeeService;
import com.huashan.yebserver.task.YootkThreadTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private NationMapper nationMapper;
    @Autowired
    private PoliticsStatusMapper politicsStatusMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private JoblevelMapper joblevelMapper;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private YootkThreadTask yootkThreadTask;

    @Override
    public AjaxResult pageList(PageEntity pageEntity, Employee employee, String[] beginDateScope) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        IPage<Employee> page = pageEntity.toPage();
        if (!StringUtils.isEmpty(employee.getName())) {
            page = employeeMapper.selectPage(page, Wrappers.<Employee>lambdaQuery().like(Employee::getName, employee.getName()));
            List<Employee> employees = page.getRecords();
            List<EmployeeVo> employeeVos = EntityUtils.toList(employees, EmployeeVo::new);
            extracted(employeeVos);
            long total = page.getTotal();
            map.put("total", total);
            map.put("employeeVos", employeeVos);
            return AjaxResult.success("获取员工列表成功", map);
        } else if (!ObjectUtils.isEmpty(employee.getPoliticid())
                || !ObjectUtils.isEmpty(employee.getNationid())
                || !ObjectUtils.isEmpty(employee.getPosid())
                || !ObjectUtils.isEmpty(employee.getJoblevelid())
                || !StringUtils.isEmpty(employee.getEngageform())
                || !ObjectUtils.isEmpty(employee.getDepartmentid())
                || !ObjectUtils.isEmpty(beginDateScope)) {
            LambdaQueryWrapper<Employee> queryWrapper = Wrappers.<Employee>lambdaQuery();
            if (!ObjectUtils.isEmpty(employee.getPoliticid())) {
                queryWrapper.eq(Employee::getPoliticid, employee.getPoliticid());
            }
            if (!ObjectUtils.isEmpty(employee.getNationid())) {
                queryWrapper.eq(Employee::getNationid, employee.getNationid());
            }
            if (!ObjectUtils.isEmpty(employee.getPosid())) {
                queryWrapper.eq(Employee::getPosid, employee.getPosid());
            }
            if (!ObjectUtils.isEmpty(employee.getJoblevelid())) {
                queryWrapper.eq(Employee::getJoblevelid, employee.getJoblevelid());
            }
            if (!StringUtils.isEmpty(employee.getEngageform())) {
                queryWrapper.eq(Employee::getEngageform, employee.getEngageform());
            }
            if (!ObjectUtils.isEmpty(employee.getDepartmentid())) {
                queryWrapper.eq(Employee::getDepartmentid, employee.getDepartmentid());
            }
            if (!ObjectUtils.isEmpty(beginDateScope)) {
                try {
                    beginDate = simpleDateFormat.parse(beginDateScope[0]);
                    endDate = simpleDateFormat.parse(beginDateScope[1]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                queryWrapper.ge(Employee::getBegindate, beginDate)
                        .le(Employee::getBegindate, endDate);
            }
            page = employeeMapper.selectPage(page, queryWrapper);
            List<Employee> employees = page.getRecords();
            List<EmployeeVo> employeeVos = EntityUtils.toList(employees, EmployeeVo::new);
            extracted(employeeVos);
            long total = page.getTotal();
            map.put("total", total);
            map.put("employeeVos", employeeVos);
            return AjaxResult.success("获取员工列表成功", map);
        } else {
            page = employeeMapper.selectPage(page, null);
            List<Employee> employees = page.getRecords();
            List<EmployeeVo> employeeVos = EntityUtils.toList(employees, EmployeeVo::new);
            extracted(employeeVos);
            long total = page.getTotal();
            map.put("total", total);
            map.put("employeeVos", employeeVos);
            return AjaxResult.success("获取员工列表成功", map);
        }
    }

    @Override
    public AjaxResult add(Employee employee) {
        if (employeeMapper.selectOne(Wrappers.<Employee>lambdaQuery().eq(Employee::getName, employee.getName())) != null) {
            return AjaxResult.error("该员工已存在");
        }
        int insert = employeeMapper.insert(employee);
        if (insert > 0) {
            //添加员工成功后，发送邮件通知
            String msg = "您好"+employee.getName()+"，您已成功加入云易办";
            yootkThreadTask.startTaskHandle(employee,msg);
            return AjaxResult.success("添加员工成功", null);
        }
        return AjaxResult.error("添加员工失败");
    }

    @Override
    public AjaxResult maxWorkId() {
        Employee employee = employeeMapper.selectOne(Wrappers.<Employee>lambdaQuery().orderByDesc(Employee::getWorkid).last("limit 1"));
        if (ObjectUtils.isEmpty(employee)) {
            return AjaxResult.error("获取工号失败");
        }
        String workid = employee.getWorkid();
        //例如工号为00000001,00000002,......,00000010,00000011,00000012,......,00000099,00000100,00000101,......,00000999,00001000,00001001,......,00009999,00010000,00010001,......,00099999,00100000,00100001,......,00999999,01000000,01000001,......,09999999,10000000,10000001,......,99999999
        //所以这里需要将字符串转换为整数
        int i = Integer.parseInt(workid);
        //然后再将整数加1
        i++;
        //然后再将整数转换为字符串
        String s = String.valueOf(i);
        //然后再将字符串转换为8位的字符串
        String format = String.format("%08d", Integer.parseInt(s));//%08d表示输出8位整数，不足8位前面补0
        return AjaxResult.success("获取工号成功", format);
    }

    @Override
    public AjaxResult editById(Employee employee) {
        int i = employeeMapper.updateById(employee);
        if (i > 0) {
            return AjaxResult.success("修改员工成功", null);
        }
        return AjaxResult.error("修改员工失败");
    }

    @Override
    public AjaxResult deleteBatchByIds(Integer[] ids) {
        int i = employeeMapper.deleteBatchIds(List.of(ids));
        if (i > 0) {
            return AjaxResult.success("删除员工成功", null);
        }
        return AjaxResult.error("删除员工失败");
    }

    @Override
    public AjaxResult allEmployees(Employee employee) {
        List<Employee> employees = employeeMapper.selectList(null);
        List<EmployeeVo> employeeVos = EntityUtils.toList(employees, EmployeeVo::new);
        extracted(employeeVos);
        return AjaxResult.success("获取员工列表成功", employeeVos);
    }

    private void extracted(List<EmployeeVo> employeeVos) {
        for (EmployeeVo employeeVo : employeeVos) {
            employeeVo.setDepartment(departmentMapper.selectById(employeeVo.getDepartmentid()));
            employeeVo.setNation(nationMapper.selectById(employeeVo.getNationid()));
            employeeVo.setPoliticsStatus(politicsStatusMapper.selectById(employeeVo.getPoliticid()));
            employeeVo.setPosition(positionMapper.selectById(employeeVo.getPosid()));
            employeeVo.setJoblevel(joblevelMapper.selectById(employeeVo.getJoblevelid()));
            employeeVo.setSalary(salaryMapper.selectById(employeeVo.getSalaryid()));
        }
    }
}
