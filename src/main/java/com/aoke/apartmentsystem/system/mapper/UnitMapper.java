package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Building;
import com.aoke.apartmentsystem.system.entity.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface UnitMapper extends BaseMapper<Unit> {

    /**
     * 通过单元名称查找单元
     *
     * @param unitName 单元名称
     * @return 单元
     */
    Unit findByUnitName(String unitName);

    /**
     * 查找单元详细信息
     *
     * @param page 分页对象
     * @param unit 单元对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Unit> findUnitDetailPage(Page page, @Param("unit") Unit unit);

    /**
     * 查找单元详细信息
     *
     * @param unit 单元对象，用于传递查询条件
     * @return List<Unit>
     */
    List<Unit> findUnitDetail(@Param("unit") Unit unit);
}
