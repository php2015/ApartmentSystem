package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.common.utils.TreeUtil;
import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Dept;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.BuildingMapper;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
import com.aoke.apartmentsystem.system.service.IBuildingService;
import com.aoke.apartmentsystem.system.service.IVillageService;
import com.aoke.apartmentsystem.third.constant.AccessToken;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
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
        building.setCreateTime(new Date());
        building.setCreateBy(AccessToken.username);
        save(building);
    }

    @Override
    public void deleteBuildings(String[] buildingIds) {
        List<String> list = Arrays.asList(buildingIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    public void updateBuilding(Building building) {
        updateById(building);
    }

    @Override
    public void updateProfile(Building building) {

    }

    @Override
    public List<DeptTree<Building>> findDepts() {
        List<Building> depts = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Building>> trees = this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }
    private List<DeptTree<Building>> convertDepts(List<Building> depts){
        List<DeptTree<Building>> trees = new ArrayList<>();
        depts.forEach(building -> {
            DeptTree<Building> tree = new DeptTree<>();
            tree.setId(String.valueOf(building.getBuildingId()));
            tree.setParentId(String.valueOf(building.getVillageId()));
            tree.setName(building.getBuildingName());
            tree.setBuilding(building);
            trees.add(tree);
        });
        return trees;
    }

    @Override
    public List<Building> findListBuilding(Building building) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(building.getBuildingName())) {
            queryWrapper.lambda().like(Building::getBuildingName, building.getBuildingName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }
}
