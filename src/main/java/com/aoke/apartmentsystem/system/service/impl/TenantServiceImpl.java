package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.FebsUtil;
import com.aoke.apartmentsystem.common.utils.MD5Util;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.system.entity.Tenant;
import com.aoke.apartmentsystem.system.entity.User;
import com.aoke.apartmentsystem.system.entity.UserRole;
import com.aoke.apartmentsystem.system.mapper.TenantMapper;
import com.aoke.apartmentsystem.system.service.ITenantService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoxinglin
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    @Override
    public Tenant findByAcount(String tenantAcount) {
        return this.baseMapper.findByAcount(tenantAcount);
    }

    @Override
    public IPage<Tenant> findTenantDetail(Tenant tenant, QueryRequest request) {
        Page<Tenant> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "tenantId", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findTenantDetailPage(page, tenant);
    }

    @Override
    public Tenant findTenantDetail(String tenantAcount) {
        Tenant param = new Tenant();
        param.setTenantAcount(tenantAcount);
        List<Tenant> tenants = this.baseMapper.findTenantDetail(param);
        return CollectionUtils.isNotEmpty(tenants) ? tenants.get(0) : null;
    }

    @Override
    @Transactional
    public void createTenant(Tenant tenant) {
        //tenant.setCreateTime(new Date());
        tenant.setStatus(Tenant.STATUS_VALID);
        tenant.setTenantPic(Tenant.DEFAULT_AVATAR);
        //tenant.setIsTab(User.TAB_OPEN);
        tenant.setPassword(MD5Util.encrypt(tenant.getTenantAcount(), User.DEFAULT_PASSWORD));
        save(tenant);
        // 保存用户角色
//        String[] roles = user.getRoleId().split(StringPool.COMMA);
//        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteTenants(String[] tenantIds) {
        List<String> list = Arrays.asList(tenantIds);
        // 删除用户
        this.removeByIds(list);
        // 删除关联角色
        //this.userRoleService.deleteUserRolesByUserId(list);
    }

    @Override
    @Transactional
    public void updateTenant(Tenant tenant) {
        // 更新用户
        tenant.setPassword(null);
        tenant.setTenantAcount(null);
        tenant.setTenantName(null);
        updateById(tenant);
    }

    @Override
    @Transactional
    public void resetPassword(String[] tenantAcounts) {

    }

    @Override
    @Transactional
    public void regist(String tenantAcount, String password) {

    }

    @Override
    @Transactional
    public void updatePassword(String tenantAcount, String password) {

    }

    @Override
    @Transactional
    public void updateTenantPic(String tenantAcount, String tenantPic) {

    }

    @Override
    @Transactional
    public void updateTheme(String tenantAcount, String theme, String isTab) {

    }

    @Override
    @Transactional
    public void updateProfile(Tenant tenant) {
        tenant.setTenantName(null);
        tenant.setTenantAcount(null);
        tenant.setPassword(null);
        updateById(tenant);
    }
}
