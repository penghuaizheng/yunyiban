package com.huashan.yebserver.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huashan.yebserver.domain.Menu;
import com.huashan.yebserver.domain.MenuRole;
import com.huashan.yebserver.domain.Role;
import com.huashan.yebserver.domain.vo.MenuVo;
import com.huashan.yebserver.mapper.MenuMapper;
import com.huashan.yebserver.mapper.MenuRoleMapper;
import com.huashan.yebserver.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Override
    public AjaxResult allMenus(Menu menu) {
        Menu grandMenu = menuMapper.selectOne(Wrappers.lambdaQuery(Menu.class).eq(Menu::getId, 1));
        MenuVo grandMenuVo = EntityUtils.toObj(grandMenu, MenuVo::new);
        List<Menu> parentmenus = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class).eq(Menu::getParentid, grandMenuVo.getId()));
        List<MenuVo> menuVos = EntityUtils.toList(parentmenus, MenuVo::new);
        if (!menuVos.isEmpty()) {
            for (MenuVo menuVo : menuVos) {
                List<Menu> childrenMenus = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class).eq(Menu::getParentid, menuVo.getId()));
                List<MenuVo> childrenMenuVos = EntityUtils.toList(childrenMenus, MenuVo::new);
                menuVo.setChildren(childrenMenuVos);
            }
        }
        grandMenuVo.setChildren(menuVos);
        List<MenuVo> menuVoList = new ArrayList<>();
        menuVoList.add(grandMenuVo);
        return AjaxResult.success("获取所有菜单",menuVoList);
    }

    @Override
    public AjaxResult tree(Integer rid) {
        List<MenuRole> menuRoles = menuRoleMapper.selectList(Wrappers.lambdaQuery(MenuRole.class).eq(MenuRole::getRid, rid));
        List<Integer> menuIds = EntityUtils.toList(menuRoles, MenuRole::getMid);
        return AjaxResult.success("获取菜单成功", menuIds);
    }
}
