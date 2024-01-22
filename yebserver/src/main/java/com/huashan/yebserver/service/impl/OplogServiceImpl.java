package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Oplog;
import com.huashan.yebserver.mapper.OplogMapper;
import com.huashan.yebserver.service.IOplogService;
import org.springframework.stereotype.Service;
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper,Oplog> implements IOplogService{
}
