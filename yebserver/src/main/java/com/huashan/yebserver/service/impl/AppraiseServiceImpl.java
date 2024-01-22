package com.huashan.yebserver.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Appraise;
import com.huashan.yebserver.mapper.AppraiseMapper;
import com.huashan.yebserver.service.IAppraiseService;
import org.springframework.stereotype.Service;
@Service
public class AppraiseServiceImpl extends ServiceImpl<AppraiseMapper,Appraise> implements IAppraiseService{
}
