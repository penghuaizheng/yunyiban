package com.huashan.yebserver.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huashan.yebserver.domain.Menu;
import com.huashan.yebserver.domain.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
@CacheNamespace(flushInterval = 300000L,eviction = ScheduledCache.class,blocking = true)
public interface MenuMapper extends BaseMapper<Menu>{
    List<Role> getRolesByMenuUrl();
}
