package com.aoke.apartmentsystem.system.controller;


import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Role;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.service.IRoleService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoxinglin
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public FebsResponse getAllRoles(Role role) {
        return new FebsResponse().success().data(roleService.findRoles(role));
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public FebsResponse roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增角色")
    @PostMapping
    @RequiresPermissions("role:add")
    public FebsResponse addRole(@Valid Role role) throws FebsException {
        try {
            this.roleService.createRole(role);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除角色")
    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) throws FebsException {
        try {
            this.roleService.deleteRoles(roleIds);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改角色")
    @PostMapping("update")
    @RequiresPermissions("role:update")
    public FebsResponse updateRole(Role role) throws FebsException {
        try {
            this.roleService.updateRole(role);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
        try {
            List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
            ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}
