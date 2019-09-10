package com.aoke.apartmentsystem.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 单元
 * @author xiaoxinglin
 */
@Data
@TableName("t_unit")
@Excel("单元信息表")
public class Unit implements Serializable {

    // 单元状态：有效
    public static final int STATUS_VALID = 1;
    // 单元状态：锁定
    public static final int STATUS_LOCK = 0;
    // 单元楼栋名称
    public static final String DEFAULT_NAME = "unit0001";

    /**
     * 单元 ID
     */
    @TableId(value = "unit_id", type = IdType.AUTO)
    private Long unitId;

    /**
     * 楼栋编号id
     */
    @ExcelField(value = "楼栋编号")
    @TableId(value = "building_id")
    private Long buildingId;

    /**
     * 所属小区编号id
     */
    @ExcelField(value = "小区编号")
    @TableId(value = "village_id")
    private Long villageId;

    /**
     * 小区名称
     */
    @ExcelField(value = "小区名称")
    @TableId(value = "unit_name")
    private String unitName;

    /**
     * 备注
     */
    @TableField("remark")
    @Size(min = 2, max = 50, message = "{range}")
    @ExcelField(value = "备注")
    private String remark;

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", buildingId=" + buildingId +
                ", villageId=" + villageId +
                ", unitName='" + unitName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Unit(Long unitId, Long buildingId, Long villageId, String unitName, @Size(min = 2, max = 50, message = "{range}") String remark) {
        this.unitId = unitId;
        this.buildingId = buildingId;
        this.villageId = villageId;
        this.unitName = unitName;
        this.remark = remark;
    }

    public Unit() {
    }
}
