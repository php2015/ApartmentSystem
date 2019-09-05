package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Room;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.RoomMapper;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
import com.aoke.apartmentsystem.system.service.IRoomService;
import com.aoke.apartmentsystem.system.service.IVillageService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Override
    public Room findByRoomName(String roomName) {
        return this.baseMapper.findByRoomName(roomName);
    }

    @Override
    public IPage<Room> findRoomDetail(Room room, QueryRequest request) {
        Page<Room> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "roomId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findRoomDetailPage(page, room);
    }

    @Override
    public Room findRoomDetail(String roomName) {
        Room param = new Room();
        param.setRoomName(roomName);
        List<Room> rooms = this.baseMapper.findRoomDetail(param);
        return CollectionUtils.isNotEmpty(rooms) ? rooms.get(0) : null;
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
//        village.setStatus(Tenant.STATUS_VALID);
////        village.setTenantPic(Tenant.DEFAULT_AVATAR);
////        //tenant.setIsTab(User.TAB_OPEN);
////        village.setPassword(MD5Util.encrypt(tenant.getTenantAcount(), User.DEFAULT_PASSWORD));
        save(room);
    }

    @Override
    @Transactional
    public void deleteRooms(String[] roomIds) {
        List<String> list = Arrays.asList(roomIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    @Transactional
    public void updateRoom(Room room) {
        // 更新用户
//        village.setPassword(null);
//        village.setTenantAcount(null);
//        village.setTenantName(null);
        updateById(room);
    }

    @Override
    @Transactional
    public void updateProfile(Room room) {

    }

    @Override
    @Transactional
    public boolean save(Room room) {
        return false;
    }
}
