package com.aoke.apartmentsystem.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoxinglin
 */
@Data
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -5200596408874170216L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单/按钮ID
     */
    @TableField("MENU_ID")
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
