package com.aoke.apartmentsystem.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Village;
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
 * 小区
 * @author xiaoxinglin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("village")
public class VillageController extends BaseController {

    Logger log = LoggerFactory.getLogger(VillageController.class);

    @Autowired
    private IVillageService villageService;


    @GetMapping("{villageName}")
    public Village getVillage(@NotBlank(message = "{required}") @PathVariable String villageName) {
        return this.villageService.findVillageDetail(villageName);
    }

    @GetMapping("check/{villageName}")
    public boolean checkVillageName(@NotBlank(message = "{required}") @PathVariable String villageName, String villageId) {
        return this.villageService.findByVillageName(villageName) == null || StringUtils.isNotBlank(villageId);
    }

    @GetMapping("list")
    @RequiresPermissions("village:view")
    public FebsResponse villageList(Village village, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.villageService.findVillageDetail(village, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增小区")
    @PostMapping
    @RequiresPermissions("village:add")
    public FebsResponse addVillage(@Valid Village village) throws FebsException {
        System.out.println(JSONObject.toJSONString(village));
        //File desc = FileUploadUtils.getAbsoluteFile(Save_Url, files.getUrl());
        //                file.transferTo(desc);
        try {
            this.villageService.createVillage(village);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增小区失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除小区")
    @GetMapping("delete/{villageIds}")
    @RequiresPermissions("village:delete")
    public FebsResponse deleteVillages(@NotBlank(message = "{required}") @PathVariable String villageIds) throws FebsException {
        try {
            String[] ids = villageIds.split(StringPool.COMMA);
            this.villageService.deleteVillages(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除小区失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改小区")
    @PostMapping("update")
    @RequiresPermissions("village:update")
    public FebsResponse updateVillage(@Valid Village village) throws FebsException {
        try {
            if (village.getVillageId() == null)
                throw new FebsException("小区ID为空");
            this.villageService.updateVillage(village);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改小区失败";
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
    @RequiresPermissions("village:export")
    public void export(QueryRequest queryRequest, Village village, HttpServletResponse response) throws FebsException {
        try {
            List<Village> villages = this.villageService.findVillageDetail(village, queryRequest).getRecords();
            ExcelKit.$Export(Village.class, response).downXlsx(villages, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
