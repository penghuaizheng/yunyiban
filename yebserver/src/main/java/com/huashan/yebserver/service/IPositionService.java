package com.huashan.yebserver.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huashan.yebserver.domain.Position;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.List;

public interface IPositionService extends IService<Position>{

    AjaxResult pageList(PageEntity pageEntity, Position position);

    AjaxResult add(Position position);

    AjaxResult editById(Position position);

    AjaxResult deleteBatchByIds(Integer[] ids);

    AjaxResult detail(Integer id);

    AjaxResult allPositions(Position position);
}
