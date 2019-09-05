package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Room;
import com.aoke.apartmentsystem.system.service.IRoomService;
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
@RequestMapping("room")
public class RoomController extends BaseController {

    Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private IRoomService roomService;


    @GetMapping("{roomName}")
    public Room getRoom(@NotBlank(message = "{required}") @PathVariable String roomName) {
        return this.roomService.findRoomDetail(roomName);
    }

    @GetMapping("check/{roomName}")
    public boolean checkRoomName(@NotBlank(message = "{required}") @PathVariable String roomName, String roomId) {
        return this.roomService.findByRoomName(roomName) == null || StringUtils.isNotBlank(roomId);
    }

    @GetMapping("list")
    @RequiresPermissions("room:view")
    public FebsResponse roomList(Room room, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roomService.findRoomDetail(room, request));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增房间")
    @PostMapping
    @RequiresPermissions("room:add")
    public FebsResponse addRoom(@Valid Room room) throws FebsException {
        try {
            this.roomService.createRoom(room);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增房间失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除房间")
    @GetMapping("delete/{roomIds}")
    @RequiresPermissions("room:delete")
    public FebsResponse deleteRooms(@NotBlank(message = "{required}") @PathVariable String roomIds) throws FebsException {
        try {
            String[] ids = roomIds.split(StringPool.COMMA);
            this.roomService.deleteRooms(ids);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除房间失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改房间")
    @PostMapping("update")
    @RequiresPermissions("room:update")
    public FebsResponse updateRoom(@Valid Room room) throws FebsException {
        try {
            if (room.getRoomId() == null)
                throw new FebsException("房间ID为空");
            this.roomService.updateRoom(room);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改房间失败";
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
    @RequiresPermissions("room:export")
    public void export(QueryRequest queryRequest, Room room, HttpServletResponse response) throws FebsException {
        try {
            List<Room> rooms = this.roomService.findRoomDetail(room, queryRequest).getRecords();
            ExcelKit.$Export(Room.class, response).downXlsx(rooms, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
