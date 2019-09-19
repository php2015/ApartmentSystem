package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Device;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.DeviceMapper;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
import com.aoke.apartmentsystem.system.service.IDeviceService;
import com.aoke.apartmentsystem.system.service.IVillageService;
import com.aoke.apartmentsystem.third.constant.AccessToken;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {
    @Override
    public Device findByDeviceName(String deviceName) {
        return this.baseMapper.findByDeviceName(deviceName);
    }

    @Override
    public IPage<Device> findDeviceDetail(Device device, QueryRequest request) {
        Page<Device> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "deviceId", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findDeviceDetailPage(page, device);
    }

    @Override
    public Device findDeviceDetail(String deviceName) {
        Device param = new Device();
        param.setDeviceName(deviceName);
        List<Device> devices = this.baseMapper.findDeviceDetail(param);
        return CollectionUtils.isNotEmpty(devices) ? devices.get(0) : null;
    }

    @Override
    @Transactional
    public void createDevice(Device device) {
        device.setDeviceId(UUID.randomUUID().toString().replace("-",""));
        device.setDeviceBelong(device.getVillageName()+device.getBuildingName()+device.getUnitName()+device.getRoomName());
        device.setDelFlag(0);
        device.setCreateBy(AccessToken.username);
        device.setCreateTime(new Date());
        save(device);
    }

    @Override
    @Transactional
    public void deleteDevices(String[] deviceIds) {
        List<String> list = Arrays.asList(deviceIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    @Transactional
    public void updateDevice(Device device) {
        // 更新用户
//        village.setPassword(null);
//        village.setTenantAcount(null);
//        village.setTenantName(null);
        device.setUpdateTime(new Date());
        updateById(device);
    }

    @Override
    @Transactional
    public void updateProfile(Device device) {

    }
}
