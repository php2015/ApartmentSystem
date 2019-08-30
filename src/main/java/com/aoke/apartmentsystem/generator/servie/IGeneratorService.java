package com.aoke.apartmentsystem.generator.servie;

import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.generator.entity.Column;
import com.aoke.apartmentsystem.generator.entity.Table;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface IGeneratorService {

    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
