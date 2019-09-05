package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface DeviceMapper extends BaseMapper<Device> {

    /**
     * 通过小区名称查找小区
     *
     * @param deviceName 小区名称
     * @return 小区
     */
    Device findByDeviceName(String deviceName);

    /**
     * 查找租户详细信息
     *
     * @param page 分页对象
     * @param device 租户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Device> findDeviceDetailPage(Page page, @Param("device") Device device);

    /**
     * 查找租户详细信息
     *
     * @param device 租户对象，用于传递查询条件
     * @return List<Device>
     */
    List<Device> findDeviceDetail(@Param("device") Device device);
}
