package com.huashan.yebserver.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huashan.yebserver.domain.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface SalaryMapper extends BaseMapper<Salary>{
}
