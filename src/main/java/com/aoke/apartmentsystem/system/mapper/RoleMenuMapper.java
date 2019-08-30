package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author xiaoxinglin
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteRoleMenus(String menuId);
}
