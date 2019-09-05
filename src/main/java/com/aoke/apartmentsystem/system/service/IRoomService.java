package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Room;
import com.aoke.apartmentsystem.system.entity.Village;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IRoomService extends IService<Room> {

    /**
     * 通过小区名称查找小区
     *
     * @param roomName 小区名称
     * @return 小区
     */
    Room findByRoomName(String roomName);

    /**
     * 查找小区详细信息
     *
     * @param request request
     * @param room    小区对象，用于传递查询条件
     * @return IPage
     */
    IPage<Room> findRoomDetail(Room room, QueryRequest request);

    /**
     * 通过小区名称查找小区详细信息
     *
     * @param roomName 小区名称
     * @return 小区信息
     */
    Room findRoomDetail(String roomName);

    /**
     * 新增小区
     *
     * @param room room
     */
    void createRoom(Room room);

    /**
     * 删除小区
     *
     * @param roomIds 小区 id数组
     */
    void deleteRooms(String[] roomIds);

    /**
     * 修改小区
     *
     * @param room room
     */
    void updateRoom(Room room);

    /**
     * 更新小区信息
     *
     * @param room 小区信息
     */
    void updateProfile(Room room);
}
