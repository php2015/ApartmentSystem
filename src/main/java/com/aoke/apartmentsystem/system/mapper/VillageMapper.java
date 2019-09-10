package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Tenant;
import com.aoke.apartmentsystem.system.entity.Village;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface VillageMapper extends BaseMapper<Village> {

    /**
     * 通过小区名称查找小区
     *
     * @param villageName 小区名称
     * @return 小区
     */
    Village findByVillageName(String villageName);

    /**
     * 查找租户详细信息
     *
     * @param page 分页对象
     * @param village 租户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<Village> findVillageDetailPage(Page page, @Param("village") Village village);

    /**
     * 查找租户详细信息
     *
     * @param village 租户对象，用于传递查询条件
     * @return List<Village>
     */
    List<Village> findVillageDetail(@Param("village") Village village);

    void deleteVillage(String villageId);

    /**
     * 查询小区
     * @return
     */
    @Select("SELECT * FROM t_village ")
    List<Village> findListVillage();
}
