package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Tenant;
import com.aoke.apartmentsystem.system.service.ITenantService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 租户
 * @author xiaoxinglin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("tenant")
public class TenantController extends BaseController {

    Logger log = LoggerFactory.getLogger(TenantController.class);

    @Autowired
    private ITenantService tenantService;


    @GetMapping("{tenantAcount}")
    public Tenant getTenant(@NotBlank(message = "{required}") @PathVariable String tenantAcount) {
        return this.tenantService.findTenantDetail(tenantAcount);
    }

    @GetMapping("check/{tenantAcount}")
    public boolean checkTenantAcount(@NotBlank(message = "{required}") @PathVariable String tenantAcount, String tenantId) {
        return this.tenantService.findByAcount(tenantAcount) == null || StringUtils.isNotBlank(tenantId);
    }

    @GetMapping("list")
    @RequiresPermissions("tenant:view")
    public FebsResponse tenantList(Tenant tenant, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.tenantService.findTenantDetail(tenant, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增租户")
    @PostMapping
    @RequiresPermissions("tenant:add")
    public FebsResponse addTenant(@Valid Tenant tenant) throws FebsException {
        try {
            this.tenantService.createTenant(tenant);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增租户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除租户")
    @GetMapping("delete/{tenantIds}")
    @RequiresPermissions("tenant:delete")
    public FebsResponse deleteTenants(@NotBlank(message = "{required}") @PathVariable String tenantIds) throws FebsException {
        try {
            String[] ids = tenantIds.split(StringPool.COMMA);
            this.tenantService.deleteTenants(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除租户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改租户")
    @PostMapping("update")
    @RequiresPermissions("tenant:update")
    public FebsResponse updateTenant(@Valid Tenant tenant) throws FebsException {
        try {
            if (tenant.getTenantId() == null)
                throw new FebsException("租户ID为空");
            this.tenantService.updateTenant(tenant);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改租户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("password/reset/{tenantAcounts}")
    @RequiresPermissions("tenant:password:reset")
    public FebsResponse resetPassword(@NotBlank(message = "{required}") @PathVariable String tenantAcounts) throws FebsException {
        try {
            String[] tenantAcountArr = tenantAcounts.split(StringPool.COMMA);
            this.tenantService.resetPassword(tenantAcountArr);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "重置租户密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

//    @PostMapping("password/update")
//    public FebsResponse updatePassword(
//            @NotBlank(message = "{required}") String oldPassword,
//            @NotBlank(message = "{required}") String newPassword) throws FebsException {
//        try {
//            User user = getCurrentUser();
//            if (!StringUtils.equals(user.getPassword(), MD5Util.encrypt(user.getUsername(), oldPassword))) {
//                throw new FebsException("原密码不正确");
//            }
//            userService.updatePassword(user.getUsername(), newPassword);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改密码失败，" + e.getMessage();
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("avatar/{image}")
//    public FebsResponse updateAvatar(@NotBlank(message = "{required}") @PathVariable String image) throws FebsException {
//        try {
//            User user = getCurrentUser();
//            this.userService.updateAvatar(user.getUsername(), image);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改头像失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("theme/update")
//    public FebsResponse updateTheme(String theme, String isTab) throws FebsException {
//        try {
//            User user = getCurrentUser();
//            this.userService.updateTheme(user.getUsername(), theme, isTab);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改系统配置失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("profile/update")
//    public FebsResponse updateProfile(User user) throws FebsException {
//        try {
//            User currentUser = getCurrentUser();
//            user.setUserId(currentUser.getUserId());
//            this.userService.updateProfile(user);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改个人信息失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }

    @GetMapping("excel")
    @RequiresPermissions("tenant:export")
    public void export(QueryRequest queryRequest, Tenant tenant, HttpServletResponse response) throws FebsException {
        try {
            List<Tenant> tenants = this.tenantService.findTenantDetail(tenant, queryRequest).getRecords();
            ExcelKit.$Export(Tenant.class, response).downXlsx(tenants, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
