package com.aoke.apartmentsystem.system.service;

import com.aoke.apartmentsystem.common.entity.DeptTree;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Unit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface IUnitService extends IService<Unit> {

    /**
     * 通过单元名称查找楼栋
     *
     * @param unitName 单元名称
     * @return 单元
     */
    Unit findByUnitName(String unitName);

    /**
     * 查找单元详细信息
     *
     * @param request request
     * @param unit    单元对象，用于传递查询条件
     * @return IPage
     */
    IPage<Unit> findUnitDetail(Unit unit, QueryRequest request);

    /**
     * 通过单元名称查找单元详细信息
     *
     * @param unitName 单元名称
     * @return 单元信息
     */
    Unit findUnitDetail(String unitName);

    /**
     * 新增单元
     *
     * @param unit unit
     */
    void createUnit(Unit unit);

    /**
     * 删除单元
     *
     * @param unitIds 单元 id数组
     */
    void deleteUnits(String[] unitIds);

    /**
     * 修改单元
     *
     * @param unit unit
     */
    void updateUnit(Unit unit);

    /**
     * 更新单元信息
     *
     * @param unit 单元信息
     */
    void updateProfile(Unit unit);

    /**
     * 获取单元树（下拉选使用）
     *
     * @return 楼栋树集合
     */
    List<DeptTree<Unit>> findDepts();

    /**
     * 查询单元
     * @return
     */
    List<Unit> findListUnit(Unit unit);
}
