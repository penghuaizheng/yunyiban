package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.EmployeeTrain;
import com.huashan.yebserver.mapper.EmployeeTrainMapper;
import com.huashan.yebserver.service.IEmployeeTrainService;
import org.springframework.stereotype.Service;
@Service
public class EmployeeTrainServiceImpl extends ServiceImpl<EmployeeTrainMapper,EmployeeTrain> implements IEmployeeTrainService{
}
