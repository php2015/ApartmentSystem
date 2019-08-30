package com.aoke.apartmentsystem.system.mapper;

import com.aoke.apartmentsystem.system.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author xiaoxinglin
 */
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 递归删除部门
     *
     * @param deptId deptId
     */
    void deleteDepts(String deptId);
}
