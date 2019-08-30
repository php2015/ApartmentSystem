package com.aoke.apartmentsystem.generator.servie.impl;

import com.aoke.apartmentsystem.common.entity.FebsConstant;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.utils.SortUtil;
import com.aoke.apartmentsystem.generator.entity.Column;
import com.aoke.apartmentsystem.generator.entity.Table;
import com.aoke.apartmentsystem.generator.mapper.GeneratorMapper;
import com.aoke.apartmentsystem.generator.servie.IGeneratorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoxinglin
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {
    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<String> getDatabases(String databaseType) {
        return generatorMapper.getDatabases(databaseType);
    }

    @Override
    public IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName) {
        Page<Table> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return generatorMapper.getTables(page, tableName, databaseType, schemaName);
    }

    @Override
    public List<Column> getColumns(String databaseType, String schemaName, String tableName) {
        return generatorMapper.getColumns(databaseType, schemaName, tableName);
    }
}
