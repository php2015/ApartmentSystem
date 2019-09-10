package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Village;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IVillageService extends IService<Village> {

    /**
     * 通过小区名称查找小区
     *
     * @param villageName 小区名称
     * @return 小区
     */
    Village findByVillageName(String villageName);

    /**
     * 查找小区详细信息
     *
     * @param request request
     * @param village    小区对象，用于传递查询条件
     * @return IPage
     */
    IPage<Village> findVillageDetail(Village village, QueryRequest request);

    /**
     * 通过小区名称查找小区详细信息
     *
     * @param villageName 小区名称
     * @return 小区信息
     */
    Village findVillageDetail(String villageName);

    /**
     * 新增小区
     *
     * @param village village
     */
    void createVillage(Village village);

    /**
     * 删除小区
     *
     * @param villageIds 小区 id数组
     */
    void deleteVillages(String[] villageIds);

    /**
     * 修改小区
     *
     * @param village village
     */
    void updateVillage(Village village);

    /**
     * 更新小区信息
     *
     * @param village 小区信息
     */
    void updateProfile(Village village);

    /**
     * 查询小区
     * @return
     */
    List<Village> findListVillage(Village village);
}
