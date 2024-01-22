package com.huashan.yebserver.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huashan.yebserver.domain.SysMsgContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface SysMsgContentMapper extends BaseMapper<SysMsgContent>{
}
