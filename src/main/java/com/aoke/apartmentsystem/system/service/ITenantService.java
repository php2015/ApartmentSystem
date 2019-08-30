package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Tenant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ITenantService extends IService<Tenant> {

    /**
     * 通过租户账号查找租户
     *
     * @param tenantAcount 租户账号
     * @return 租户
     */
    Tenant findByAcount(String tenantAcount);

    /**
     * 查找租户详细信息
     *
     * @param request request
     * @param tenant    租户对象，用于传递查询条件
     * @return IPage
     */
    IPage<Tenant> findTenantDetail(Tenant tenant, QueryRequest request);

    /**
     * 通过租户账号查找租户详细信息
     *
     * @param tenantAcount 租户账号
     * @return 租户信息
     */
    Tenant findTenantDetail(String tenantAcount);

    /**
     * 新增租户
     *
     * @param tenant tenant
     */
    void createTenant(Tenant tenant);

    /**
     * 删除租户
     *
     * @param tenantIds 租户 id数组
     */
    void deleteTenants(String[] tenantIds);

    /**
     * 修改租户
     *
     * @param tenant tenant
     */
    void updateTenant(Tenant tenant);

    /**
     * 重置密码
     *
     * @param tenantAcounts 租户名数组
     */
    void resetPassword(String[] tenantAcounts);

    /**
     * 注册租户
     *
     * @param tenantAcount 租户名
     * @param password 密码
     */
    void regist(String tenantAcount, String password);

    /**
     * 修改密码
     *
     * @param tenantAcount 租户名
     * @param password 新密码
     */
    void updatePassword(String tenantAcount, String password);

    /**
     * 更新租户头像
     *
     * @param tenantAcount 租户名
     * @param tenantPic   租户头像
     */
    void updateTenantPic(String tenantAcount, String tenantPic);

    /**
     * 修改租户系统配置（个性化配置）
     *
     * @param tenantAcount 租户名称
     * @param theme    主题风格
     * @param isTab    是否开启 TAB
     */
    void updateTheme(String tenantAcount, String theme, String isTab);

    /**
     * 更新租户个人信息
     *
     * @param tenant 租户个人信息
     */
    void updateProfile(Tenant tenant);
}
