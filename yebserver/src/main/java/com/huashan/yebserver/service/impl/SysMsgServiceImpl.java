package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.SysMsg;
import com.huashan.yebserver.mapper.SysMsgMapper;
import com.huashan.yebserver.service.ISysMsgService;
import org.springframework.stereotype.Service;
@Service
public class SysMsgServiceImpl extends ServiceImpl<SysMsgMapper,SysMsg> implements ISysMsgService{
}
