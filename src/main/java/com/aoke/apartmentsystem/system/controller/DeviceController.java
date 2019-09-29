package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Device;
import com.aoke.apartmentsystem.system.service.IDeviceService;
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
 * 设备
 * @author xiaoxinglin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("device")
public class DeviceController extends BaseController {

    Logger log = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private IDeviceService deviceService;


    @GetMapping("{deviceName}")
    public Device getDevice(@NotBlank(message = "{required}") @PathVariable String deviceName) {
        return this.deviceService.findDeviceDetail(deviceName);
    }

    @GetMapping("check/{deviceName}")
    public boolean checkDeviceName(@NotBlank(message = "{required}") @PathVariable String deviceName, String deviceId) {
        return this.deviceService.findByDeviceName(deviceName) == null || StringUtils.isNotBlank(deviceId);
    }

    @GetMapping("list")
    @RequiresPermissions("device:view")
    public FebsResponse deviceList(Device device, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.deviceService.findDeviceDetail(device, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增设备")
    @PostMapping
    @RequiresPermissions("device:add")
    public FebsResponse addDevice(@Valid Device device) throws FebsException {
        try {
            this.deviceService.createDevice(device);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增设备失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     *
     * @param
     * @return
     * @throws FebsException
     */
    @Log("同步第三方设备")
    @PostMapping("third/device")
    public FebsResponse addDeviceThird() throws FebsException {
        try {
            this.deviceService.createDeviceThird();
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "同步失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     *
     * @param deviceIds
     * @return
     * @throws FebsException
     */
    @Log("删除设备")
    @GetMapping("delete/{deviceIds}")
    @RequiresPermissions("device:delete")
    public FebsResponse deleteDevices(@NotBlank(message = "{required}") @PathVariable String deviceIds) throws FebsException {
        try {
            String[] ids = deviceIds.split(StringPool.COMMA);
            this.deviceService.deleteDevices(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除设备失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改设备")
    @PostMapping("update")
    @RequiresPermissions("device:update")
    public FebsResponse updateDevice(@Valid Device device) throws FebsException {
        try {
            if (device.getDeviceId() == null)
                throw new FebsException("设备ID为空");
            this.deviceService.updateDevice(device);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改设备失败";
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
    @RequiresPermissions("device:export")
    public void export(QueryRequest queryRequest, Device device, HttpServletResponse response) throws FebsException {
        try {
            List<Device> devices = this.deviceService.findDeviceDetail(device, queryRequest).getRecords();
            ExcelKit.$Export(Device.class, response).downXlsx(devices, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
