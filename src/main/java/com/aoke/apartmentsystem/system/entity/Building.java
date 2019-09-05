package com.aoke.apartmentsystem.system.entity;

import com.aoke.apartmentsystem.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 楼栋
 * @author xiaoxinglin
 */
@Data
@TableName("t_building")
@Excel("楼栋信息表")
public class Building implements Serializable {

    // 楼栋状态：有效
    public static final int STATUS_VALID = 1;
    // 楼栋状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认楼栋图片
    public static final String DEFAULT_AVATAR = "default_building.jpg";
    // 默认楼栋名称
    public static final String DEFAULT_NAME = "building01";

    /**
     * 楼栋 ID
     */
    @TableId(value = "building_id", type = IdType.AUTO)
    private Long buildingId;

    /**
     * 所属小区编号id(小区标识)
     */
    @TableId(value = "village_id")
    private Long villageId;

    /**
     * 楼栋名称
     */
    @TableField("building_name")
    @Size(min = 2, max = 30, message = "{range}")
    @ExcelField(value = "楼栋名称")
    private String buildingName;

    /**
     * 楼栋类型
     */
    @TableField("building_type")
    private int buildingType;

    /**
     * 楼栋数量
     */
    @TableField("building_num")
    private int buildingNum;

    /**
     * 备注
     */
    @TableField("remark")
    @Size(min = 2, max = 50, message = "{range}")
    @ExcelField(value = "备注")
    private String remark;

    /**
     * 创建者
     */
    @TableField("create_by")
    @ExcelField(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    @ExcelField(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ExcelField(value = "更新时间", writeConverter = TimeConverter.class)
    private Date updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    private int delFlag;

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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", villageId=" + villageId +
                ", buildingName='" + buildingName + '\'' +
                ", buildingType=" + buildingType +
                ", buildingNum=" + buildingNum +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
