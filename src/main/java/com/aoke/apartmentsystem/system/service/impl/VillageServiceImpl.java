package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Village;
import com.aoke.apartmentsystem.system.mapper.VillageMapper;
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
public class VillageServiceImpl extends ServiceImpl<VillageMapper, Village> implements IVillageService {
    @Override
    public Village findByVillageName(String villageName) {
        return this.baseMapper.findByVillageName(villageName);
    }

    @Override
    public IPage<Village> findVillageDetail(Village village, QueryRequest request) {
        Page<Village> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "villageId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findVillageDetailPage(page, village);
    }

    @Override
    public Village findVillageDetail(String villageName) {
        Village param = new Village();
        param.setVillageName(villageName);
        List<Village> villages = this.baseMapper.findVillageDetail(param);
        return CollectionUtils.isNotEmpty(villages) ? villages.get(0) : null;
    }

    @Override
    @Transactional
    public void createVillage(Village village) {
        Random random = new Random();
        village.setVillageId(random.nextLong());
        village.setBuildingNum(20);
        village.setRoomNum(100);
        village.setCreateTime(new Date());
        village.setCreateBy(AccessToken.username);
        save(village);
    }

    @Override
    @Transactional
    public void deleteVillages(String[] villageIds) {
        List<String> list = Arrays.asList(villageIds);
        // 删除用户
        this.removeByIds(list);
        for (String villageId : villageIds) {
            // 递归删除这些菜单/按钮
            this.baseMapper.deleteVillage(villageId);
        }
    }

    @Override
    @Transactional
    public void updateVillage(Village village) {
        // 更新用户
//        village.setPassword(null);
//        village.setTenantAcount(null);
//        village.setTenantName(null);
        updateById(village);
    }

    @Override
    @Transactional
    public void updateProfile(Village village) {

    }

    @Override
    @Transactional
    public boolean save(Village entity) {
        this.baseMapper.insert(entity);
        return true;
    }

    @Override
    public List<Village> findListVillage(Village village) {
        QueryWrapper<Village> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(village.getVillageName())) {
            queryWrapper.lambda().like(Village::getVillageName, village.getVillageName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean saveBatch(Collection<Village> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Village> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<Village> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(Village entity) {
        return false;
    }

    @Override
    public boolean update(Village entity, Wrapper<Village> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Village> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Village entity) {
        return false;
    }

    @Override
    public Village getById(Serializable id) {
        return null;
    }

    @Override
    public Collection<Village> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public Collection<Village> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public Village getOne(Wrapper<Village> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Village> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Village> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count(Wrapper<Village> queryWrapper) {
        return 0;
    }

    @Override
    public List<Village> list(Wrapper<Village> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Village> page(IPage<Village> page, Wrapper<Village> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Village> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Village> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Village> page, Wrapper<Village> queryWrapper) {
        return null;
    }
}
