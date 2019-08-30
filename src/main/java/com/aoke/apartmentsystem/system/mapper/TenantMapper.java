package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     * 通过租户名查找租户
     *
     * @param tenantAcount 租户名
     * @return 租户
     */
    Tenant findByAcount(String tenantAcount);

    /**
     * 查找租户详细信息
     *
     * @param page 分页对象
     * @param tenant 租户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Tenant> findTenantDetailPage(Page page, @Param("tenant") Tenant tenant);

    /**
     * 查找租户详细信息
     *
     * @param tenant 租户对象，用于传递查询条件
     * @return List<Tenant>
     */
    List<Tenant> findTenantDetail(@Param("tenant") Tenant tenant);
}
