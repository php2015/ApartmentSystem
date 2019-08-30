package com.aoke.apartmentsystem.generator.entity;

import lombok.Data;

/**
 * @author xiaoxinglin
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getKey() {
        return isKey;
    }

    public void setKey(Boolean key) {
        isKey = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
