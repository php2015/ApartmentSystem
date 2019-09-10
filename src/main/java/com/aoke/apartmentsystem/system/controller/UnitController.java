package com.aoke.apartmentsystem.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.common.utils.StringUtil;
import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Dept;
import com.aoke.apartmentsystem.system.entity.Unit;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.service.IBuildingService;
import com.aoke.apartmentsystem.system.service.IRoomService;
import com.aoke.apartmentsystem.system.service.IUnitService;
import com.aoke.apartmentsystem.system.service.IVillageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 楼栋
 * @author xiaoxinglin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("building")
public class BuildingController extends BaseController {

    Logger log = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IVillageService villageService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IUnitService unitService;

    @GetMapping("select/tree")
    public List<DeptTree<Building>> getDeptTree() throws FebsException {
        try {
            return this.buildingService.findDepts();
        } catch (Exception e) {
            String message = "获取楼栋树失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("buildings")
    public FebsResponse getAllRoles(Building building) {
        return new FebsResponse().success().data(buildingService.findListBuilding(building));
    }


    @GetMapping("{buildingName}")
    public Building getVillage(@NotBlank(message = "{required}") @PathVariable String buildingName) {
        return this.buildingService.findBuildingDetail(buildingName);
    }

    @GetMapping("check/{buildingName}")
    public boolean checkVillageName(@NotBlank(message = "{required}") @PathVariable String buildingName, String buildingId) {
        return this.buildingService.findByBuildingName(buildingName) == null || StringUtils.isNotBlank(buildingId);
    }

    @GetMapping("list")
    @RequiresPermissions("building:view")
    public FebsResponse buildingList(Building building, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.buildingService.findBuildingDetail(building, request));
        Long kzRoom = this.roomService.findkzRoomCount();
        Long yzRoom = this.roomService.findyzRoomCount();
        Long totalRoom = this.roomService.findtotalRoomCount();
        Long outTimeRoom = this.roomService.findoutTimeRoomCount();
        dataTable.put("kzRoom",kzRoom);
        dataTable.put("yzRoom",yzRoom);
        dataTable.put("totalRoom",totalRoom);
        dataTable.put("outTimeRoom",outTimeRoom);
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增楼栋")
    @PostMapping
    @RequiresPermissions("building:add")
    public FebsResponse addBuilding(@Valid Building building) throws FebsException {
        System.out.println(">>>>>:"+building.getVillageId());
        try {
            this.buildingService.createBuilding(building);
            Unit unit = new Unit(null,building.getBuildingId(),building.getVillageId(),building.getBuildingName()+"楼栋"+ StringUtil.lpad(4,new Long(building.getBuildingId()).intValue())+"单元","");
            this.unitService.createUnit(unit);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增楼栋失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除楼栋")
    @GetMapping("delete/{buildingIds}")
    @RequiresPermissions("building:delete")
    public FebsResponse deleteBuildings(@NotBlank(message = "{required}") @PathVariable String buildingIds) throws FebsException {
        try {
            String[] ids = buildingIds.split(StringPool.COMMA);
            this.buildingService.deleteBuildings(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除楼栋失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改楼栋")
    @PostMapping("update")
    @RequiresPermissions("building:update")
    public FebsResponse updateBuilding(@Valid Building building) throws FebsException {
        try {
            if (building.getBuildingId() == null)
                throw new FebsException("楼栋ID为空");
            this.buildingService.updateBuilding(building);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改楼栋失败";
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
    @RequiresPermissions("building:export")
    public void export(QueryRequest queryRequest, Building building, HttpServletResponse response) throws FebsException {
        try {
            List<Building> buildings = this.buildingService.findBuildingDetail(building, queryRequest).getRecords();
            ExcelKit.$Export(Building.class, response).downXlsx(buildings, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
