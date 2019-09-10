package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 通过小区名称查找小区
     *
     * @param roomName 小区名称
     * @return 小区
     */
    Room findByRoomName(String roomName);

    /**
     * 查找租户详细信息
     *
     * @param page 分页对象
     * @param room 租户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Room> findRoomDetailPage(Page page, @Param("room") Room room);

    /**
     * 查找租户详细信息
     *
     * @param room 租户对象，用于传递查询条件
     * @return List<Room>
     */
    List<Room> findRoomDetail(@Param("room") Room room);

    /**
     * 所有房间总数
     *
     * @return Long
     */
    Long findtotalRoomCount();

    /**
     * 空置房间总数
     *
     * @return Long
     */
    Long findkzRoomCount();

    /**
     * 已租房间总数
     *
     * @return Long
     */
    Long findyzRoomCount();

    /**
     * 到期房间总数
     *
     * @return Long
     */
    Long findoutTimeRoomCount();
}
