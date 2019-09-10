package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.common.utils.StringUtil;
import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Unit;
import com.aoke.apartmentsystem.system.service.IBuildingService;
import com.aoke.apartmentsystem.system.service.IRoomService;
import com.aoke.apartmentsystem.system.service.IUnitService;
import com.aoke.apartmentsystem.system.service.IVillageService;
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
 * 单元
 * @author xiaoxinglin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("unit")
public class UnitController extends BaseController {

    Logger log = LoggerFactory.getLogger(UnitController.class);

    @Autowired
    private IUnitService unitService;


    @GetMapping("units")
    public FebsResponse getAllUnits(Unit unit) {
        return new FebsResponse().success().data(unitService.findListUnit(unit));
    }


    @GetMapping("{unitName}")
    public Unit getUnit(@NotBlank(message = "{required}") @PathVariable String unitName) {
        return this.unitService.findUnitDetail(unitName);
    }

    @GetMapping("check/{unitName}")
    public boolean checkUnitName(@NotBlank(message = "{required}") @PathVariable String unitName, String unitId) {
        return this.unitService.findByUnitName(unitName) == null || StringUtils.isNotBlank(unitId);
    }

    @GetMapping("list")
    @RequiresPermissions("unit:view")
    public FebsResponse unitList(Unit unit, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.unitService.findUnitDetail(unit, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("删除单元")
    @GetMapping("delete/{unitIds}")
    @RequiresPermissions("unit:delete")
    public FebsResponse deleteUnits(@NotBlank(message = "{required}") @PathVariable String unitIds) throws FebsException {
        try {
            String[] ids = unitIds.split(StringPool.COMMA);
            this.unitService.deleteUnits(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除单元失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改单元")
    @PostMapping("update")
    @RequiresPermissions("unit:update")
    public FebsResponse updateUnit(@Valid Unit unit) throws FebsException {
        try {
            if (unit.getUnitId() == null)
                throw new FebsException("单元ID为空");
            this.unitService.updateUnit(unit);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改单元失败";
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
    @RequiresPermissions("unit:export")
    public void export(QueryRequest queryRequest, Unit unit, HttpServletResponse response) throws FebsException {
        try {
            List<Unit> units = this.unitService.findUnitDetail(unit, queryRequest).getRecords();
            ExcelKit.$Export(Unit.class, response).downXlsx(units, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
