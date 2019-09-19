package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.common.utils.TreeUtil;
import com.aoke.apartmentsystem.system.entity.Unit;
import com.aoke.apartmentsystem.system.mapper.UnitMapper;
import com.aoke.apartmentsystem.system.service.IUnitService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements IUnitService {

    @Override
    public Unit findByUnitName(String unitName) {
        return this.baseMapper.findByUnitName(unitName);
    }

    @Override
    public IPage<Unit> findUnitDetail(Unit unit, QueryRequest request) {
        Page<Unit> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "unitId", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findUnitDetailPage(page, unit);
    }

    @Override
    public Unit findUnitDetail(String unitName) {
        Unit param = new Unit();
        param.setUnitName(unitName);
        List<Unit> units = this.baseMapper.findUnitDetail(param);
        return CollectionUtils.isNotEmpty(units) ? units.get(0) : null;
    }

    @Override
    public void createUnit(Unit unit) {
//        unit.setCreateTime(new Date());
//        unit.setCreateBy(AccessToken.username);
        save(unit);
    }

    @Override
    public void deleteUnits(String[] unitIds) {
        List<String> list = Arrays.asList(unitIds);
        // 删除用户
        this.removeByIds(list);
    }

    @Override
    public void updateUnit(Unit unit) {

    }

    @Override
    public void updateProfile(Unit unit) {

    }

    @Override
    public List<DeptTree<Unit>> findDepts() {
        List<Unit> depts = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Unit>> trees = this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }
    private List<DeptTree<Unit>> convertDepts(List<Unit> units){
        List<DeptTree<Unit>> trees = new ArrayList<>();
        units.forEach(unit -> {
            DeptTree<Unit> tree = new DeptTree<>();
            tree.setId(String.valueOf(unit.getUnitId()));
            tree.setParentId(String.valueOf(unit.getBuildingId()));
            tree.setName(unit.getUnitName());
            //tree.setBuilding(building);
            trees.add(tree);
        });
        return trees;
    }

    @Override
    public List<Unit> findListUnit(Unit unit) {
        QueryWrapper<Unit> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(unit.getUnitName())) {
            queryWrapper.lambda().like(Unit::getUnitName, unit.getUnitName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }
}
