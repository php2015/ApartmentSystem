package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Device;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDeviceService extends IService<Device> {

    /**
     * 通过设备名称查找小区
     *
     * @param deviceName 设备名称
     * @return 设备
     */
    Device findByDeviceName(String deviceName);

    /**
     * 查找设备详细信息
     *
     * @param request request
     * @param device    设备对象，用于传递查询条件
     * @return IPage
     */
    IPage<Device> findDeviceDetail(Device device, QueryRequest request);

    /**
     * 通过设备名称查找小区详细信息
     *
     * @param deviceName 设备名称
     * @return 设备信息
     */
    Device findDeviceDetail(String deviceName);

    /**
     * 新增设备
     *
     * @param device device
     */
    void createDevice(Device device);

    /**
     * 删除设备
     *
     * @param deviceIds 设备 id数组
     */
    void deleteDevices(String[] deviceIds);

    /**
     * 修改设备
     *
     * @param device device
     */
    void updateDevice(Device device);

    /**
     * 更新设备信息
     *
     * @param device 设备信息
     */
    void updateProfile(Device device);
}
