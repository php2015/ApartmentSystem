package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Building;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IBuildingService extends IService<Building> {

    /**
     * 通过小区名称查找小区
     *
     * @param buildingName 小区名称
     * @return 小区
     */
    Building findByBuildingName(String buildingName);

    /**
     * 查找小区详细信息
     *
     * @param request request
     * @param building    小区对象，用于传递查询条件
     * @return IPage
     */
    IPage<Building> findBuildingDetail(Building building, QueryRequest request);

    /**
     * 通过小区名称查找小区详细信息
     *
     * @param buildingName 小区名称
     * @return 小区信息
     */
    Building findBuildingDetail(String buildingName);

    /**
     * 新增小区
     *
     * @param building building
     */
    void createBuilding(Building building);

    /**
     * 删除小区
     *
     * @param buildingIds 小区 id数组
     */
    void deleteBuildings(String[] buildingIds);

    /**
     * 修改小区
     *
     * @param building building
     */
    void updateBuilding(Building building);

    /**
     * 更新小区信息
     *
     * @param building 小区信息
     */
    void updateProfile(Building building);
}
