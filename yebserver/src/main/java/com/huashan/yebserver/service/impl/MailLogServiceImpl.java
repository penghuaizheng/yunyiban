package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.MailLog;
import com.huashan.yebserver.mapper.MailLogMapper;
import com.huashan.yebserver.service.IMailLogService;
import org.springframework.stereotype.Service;
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper,MailLog> implements IMailLogService{
}
