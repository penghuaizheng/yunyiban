package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.EmployeeEc;
import com.huashan.yebserver.mapper.EmployeeEcMapper;
import com.huashan.yebserver.service.IEmployeeEcService;
import org.springframework.stereotype.Service;
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper,EmployeeEc> implements IEmployeeEcService{
}
