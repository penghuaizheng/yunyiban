package com.huashan.yebserver.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huashan.yebserver.domain.*;
import com.huashan.yebserver.domain.vo.EmployeeVo;
import com.huashan.yebserver.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/yebserver/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private INationService nationService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private ISalaryService salaryService;

    @GetMapping("/page")
    @ApiOperation(value = "获取员工列表")
    public AjaxResult page(PageEntity pageEntity, Employee employee, String[] beginDateScope) {
        return employeeService.pageList(pageEntity, employee, beginDateScope);
    }

    @GetMapping("/list")
    public AjaxResult list(Employee employee) {
        return employeeService.allEmployees(employee);
    }

    @PostMapping("/push")
    @ApiOperation(value = "添加员工")
    public AjaxResult add(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }

    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Employee employee) {
        return employeeService.editById(employee);
    }

    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return employeeService.deleteBatchByIds(ids);
    }

    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(employeeService.getById(id));
    }

    @GetMapping("/maxWorkId")
    public AjaxResult maxWorkId() {
        return employeeService.maxWorkId();
    }

    //通过流的形式传出去：httpServletResponse response
    // produces通过流形式传输，解决乱码
    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) throws UnsupportedEncodingException {
        //获取当前员工的数据-具体的数据
        AjaxResult ajaxResult = employeeService.allEmployees(null);
        //获取数据
        List<EmployeeVo> list = (List<EmployeeVo>) ajaxResult.get("data");
        /**
         * 导出的参数：
         *  导出员工的数据：（文件名的名字，sheet表名，excel的版本）
         *  excel ：
         *  03： HSSF － 提供读写Microsoft Excel XLS格式档案的功能。03打不开07
         *  07： XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。07能打开03
         *
         *   03的HSSF比07的XSSF优点：
         *      1.速度快
         *      2.兼容性好。03打不开07， 07能打开03
         */
        ExportParams params = new ExportParams("员工表", "sheet员工表", ExcelType.HSSF);

        /**
         * ExcelExportUtil导出工具类：（导出的参数、对象类名.class、具体的数据）
         *  返回的是Workbook。是poi的一个类。相当于工作簿（工作簿就是一个Excel）
         */
        Workbook book = ExcelExportUtil.exportExcel(params, EmployeeVo.class, list);

        /**
         * response。输出流形式 输出workboo
         *
         * 导出需要一些请求头的响应信息
         * 防止中文乱码
         */
        ServletOutputStream out = null;
        try {
            //流形式，设置一个头
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" +
                    URLEncoder.encode("员工表.xls", "UTF-8"));
            out = response.getOutputStream();
            book.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public AjaxResult importEmployee(MultipartFile file) {
        //创建导入对象
        ImportParams params = new ImportParams();
        //去掉标题第一行
        params.setTitleRows(1);
        /**
         * 导入 流的形式、POJO对象类的字节码、数据--- 返回的是数据
         *
         * 导入完成后，pojo.Employee-nation是有值的
         * pojo.nation拿到的就是name 是因为：
         *  Excel注解，ExcelEntiy注解：
         *      能通过汉族名字获取到它是nation.ExcelEntiy就知道了是对象里面的，从而得到name
         *  如何获得name的id呢？
         *
         *      for循环
         *      重写了hashcode方法，也准备了有参构造。
         *      1.从excel导入的数据中，拿到了nation对象，对象里面有name，id是空的
         *      2.查询所有的nationList,获取对象索引下标：重写了equals和hashcode，
         *          通过name名字去nationList比较。能获取一样的对象.从而得到下标。
         *      3.通过nationList.get(下标).getId() 获取完整的对象：有id，name\
         *      4.在从id，name中获取id。 将id放入到employee中。
         *
         *  if插入：saveBatch批量插入。 插入集合
         *
         */
        List<Nation> nationlList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Position> positionList = positionService.list();
        List<Salary> salaryList = salaryService.list();
        try {
            List<EmployeeVo> list = ExcelImportUtil.importExcel(file.getInputStream(), EmployeeVo.class, params);
            for (EmployeeVo employeeVo : list) {
                //民族id
                Nation nation = new Nation(employeeVo.getNation().getName());
                if (nationlList.contains(nation)) {
                    employeeVo.setNationid(nationlList.get(nationlList.indexOf(nation)).getId());
                }
                //政治面貌id
                PoliticsStatus politicsStatus = new PoliticsStatus(employeeVo.getPoliticsStatus().getName());
                if (politicsStatusList.contains(politicsStatus)) {
                    employeeVo.setPoliticid(politicsStatusList.get(politicsStatusList.indexOf(
                            politicsStatus)).getId());
                }
                //部门id
                Department department = new Department(employeeVo.getDepartment().getName());
                if (departmentList.contains(department)) {
                    employeeVo.setDepartmentid(departmentList.get(departmentList.indexOf(
                            department)).getId());
                }
                //职称id
                Joblevel joblevel = new Joblevel(employeeVo.getJoblevel().getName());
                if (joblevelList.contains(joblevel)) {
                    employeeVo.setJoblevelid(joblevelList.get(joblevelList.indexOf(
                            joblevel)).getId());
                }
                //职位id
                Position position = new Position(employeeVo.getPosition().getName());
                if (positionList.contains(position)) {
                    employeeVo.setPosid(positionList.get(positionList.indexOf(
                            position)).getId());
                }
                //工资账套id
                Salary salary = new Salary(employeeVo.getSalary().getName());
                if (salaryList.contains(salary)) {
                    employeeVo.setSalaryid(salaryList.get(salaryList.indexOf(
                            salary)).getId());
                }
            }
            List<Employee> list1 = EntityUtils.toList(list, Employee::new);
            if (employeeService.saveBatch(list1)) {
                return AjaxResult.success("导入成功!", null);
            }
            return AjaxResult.error("导入失败!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导入失败!");
    }

}
