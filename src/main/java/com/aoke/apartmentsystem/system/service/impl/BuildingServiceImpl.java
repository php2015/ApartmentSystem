package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.BuildingMapper;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
import com.aoke.apartmentsystem.system.service.IBuildingService;
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
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

    @Override
    public Building findByBuildingName(String buildingName) {
        return this.baseMapper.findByBuildingName(buildingName);
    }

    @Override
    public IPage<Building> findBuildingDetail(Building building, QueryRequest request) {
        Page<Building> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "buildingId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findBuildingDetailPage(page, building);
    }

    @Override
    public Building findBuildingDetail(String buildingName) {
        Building param = new Building();
        param.setBuildingName(buildingName);
        List<Building> buildings = this.baseMapper.findBuildingDetail(param);
        return CollectionUtils.isNotEmpty(buildings) ? buildings.get(0) : null;
    }

    @Override
    public void createBuilding(Building building) {

    }

    @Override
    public void deleteBuildings(String[] buildingIds) {
        List<String> list = Arrays.asList(buildingIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    public void updateBuilding(Building building) {

    }

    @Override
    public void updateProfile(Building building) {

    }
}
