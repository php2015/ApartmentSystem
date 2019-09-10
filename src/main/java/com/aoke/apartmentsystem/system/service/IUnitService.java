package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Dept;
import com.aoke.apartmentsystem.system.entity.Village;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IBuildingService extends IService<Building> {

    /**
     * 通过楼栋名称查找楼栋
     *
     * @param buildingName 楼栋名称
     * @return 楼栋
     */
    Building findByBuildingName(String buildingName);

    /**
     * 查找楼栋详细信息
     *
     * @param request request
     * @param building    楼栋对象，用于传递查询条件
     * @return IPage
     */
    IPage<Building> findBuildingDetail(Building building, QueryRequest request);

    /**
     * 通过楼栋名称查找楼栋详细信息
     *
     * @param buildingName 楼栋名称
     * @return 楼栋信息
     */
    Building findBuildingDetail(String buildingName);

    /**
     * 新增楼栋
     *
     * @param building building
     */
    void createBuilding(Building building);

    /**
     * 删除楼栋
     *
     * @param buildingIds 楼栋 id数组
     */
    void deleteBuildings(String[] buildingIds);

    /**
     * 修改楼栋
     *
     * @param building building
     */
    void updateBuilding(Building building);

    /**
     * 更新楼栋信息
     *
     * @param building 楼栋信息
     */
    void updateProfile(Building building);

    /**
     * 获取楼栋树（下拉选使用）
     *
     * @return 楼栋树集合
     */
    List<DeptTree<Building>> findDepts();

    /**
     * 查询楼栋
     * @return
     */
    List<Building> findListBuilding(Building building);
}
