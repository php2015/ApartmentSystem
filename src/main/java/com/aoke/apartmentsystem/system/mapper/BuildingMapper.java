package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Building;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface BuildingMapper extends BaseMapper<Building> {

    /**
     * 通过小区名称查找小区
     *
     * @param buildingName 小区名称
     * @return 小区
     */
    Building findByBuildingName(String buildingName);

    /**
     * 查找租户详细信息
     *
     * @param page 分页对象
     * @param building 租户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Building> findBuildingDetailPage(Page page, @Param("building") Building building);

    /**
     * 查找租户详细信息
     *
     * @param building 租户对象，用于传递查询条件
     * @return List<Building>
     */
    List<Building> findBuildingDetail(@Param("building") Building building);
}
