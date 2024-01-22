package com.huashan.yebserver.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huashan.yebserver.domain.Employee;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.springframework.transaction.annotation.Transactional;
@Mapper
@Transactional(rollbackFor = Exception.class)
@CacheNamespace(flushInterval = 300000L,eviction = ScheduledCache.class,blocking = true)
public interface EmployeeMapper extends BaseMapper<Employee>{
}
