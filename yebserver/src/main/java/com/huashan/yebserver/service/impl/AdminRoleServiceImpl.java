package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.AdminRole;
import com.huashan.yebserver.mapper.AdminRoleMapper;
import com.huashan.yebserver.service.IAdminRoleService;
import org.springframework.stereotype.Service;
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper,AdminRole> implements IAdminRoleService{
}
