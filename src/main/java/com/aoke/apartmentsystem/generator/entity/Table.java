package com.aoke.apartmentsystem.generator.entity;

import lombok.Data;

/**
 * @author xiaoxinglin
 */
@Data
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDataRows() {
        return dataRows;
    }

    public void setDataRows(Long dataRows) {
        this.dataRows = dataRows;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
