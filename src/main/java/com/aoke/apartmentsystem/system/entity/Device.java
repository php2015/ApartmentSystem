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
 * 设备
 * @author xiaoxinglin
 */
@Data
@TableName("t_device")
@Excel("设备信息表")
public class Device implements Serializable {

    // 设备状态：有效
    public static final int STATUS_VALID = 1;
    // 设备状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认设备名称
    public static final String DEFAULT_NAME = "device01";

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long Id;

    /**
     * 设备 ID
     */
    @TableField("device_id")
    @ExcelField(value = "设备编号")
    private String deviceId;

    /**
     * 设备名称
     */
    @TableField("device_name")
    @Size(min = 2, max = 50, message = "{range}")
    @ExcelField(value = "设备名称")
    private String deviceName;

    /**
     * 设备类型（0水表、1电表、2烟感、3智能锁、4其他）
     */
    @TableField("device_type")
    @ExcelField(value = "设备类型")
    private int deviceType;

    /**
     * 设备型号
     */
    @TableField("device_model")
    @Size(min = 2, max = 50, message = "{range}")
    @ExcelField(value = "设备型号")
    private String deviceModel;

    /**
     * 状态(0离线、1在线、2停用)
     */
    @TableField("device_status")
    @ExcelField(value = "设备状态")
    private int deviceStatus;

    /**
     * village_id
     */
    @TableField(value = "village_id")
    private Long villageId;

    /**
     * building_id
     */
    @TableField(value = "building_id")
    private Long buildingId;

    /**
     * unit_id
     */
    @TableField(value = "unit_id")
    private Long unitId;

    /**
     * room_id
     */
    @TableField(value = "room_id")
    private Long roomId;

    /**
     * 所属(市/区/小区楼栋房间)
     */
    @TableField("device_belong")
    @Size(min = 2, max = 50, message = "{range}")
    @ExcelField(value = "设备所属")
    private String deviceBelong;

    /**
     * 有效年限
     */
    @TableField("effective_year")
    @ExcelField(value = "有效年限")
    private int effectiveYear;

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

    /**
     * 标识是否为第三方设备(0否、1是)
     */
    @TableField("third_device")
    private int thirdDevice;

    /**
     * 小区名称
     */
    @ExcelField(value = "小区")
    @TableField(exist = false)
    private String villageName;

    /**
     * 楼栋名称
     */
    @ExcelField(value = "楼栋")
    @TableField(exist = false)
    private String buildingName;

    /**
     * 单元名称
     */
    @ExcelField(value = "单元")
    @TableField(exist = false)
    private String unitName;

    /**
     * 房间名称
     */
    @ExcelField(value = "房间")
    @TableField(exist = false)
    private String roomName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceBelong() {
        return deviceBelong;
    }

    public void setDeviceBelong(String deviceBelong) {
        this.deviceBelong = deviceBelong;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getEffectiveYear() {
        return effectiveYear;
    }

    public void setEffectiveYear(int effectiveYear) {
        this.effectiveYear = effectiveYear;
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

    public int getThirdDevice() {
        return thirdDevice;
    }

    public void setThirdDevice(int thirdDevice) {
        this.thirdDevice = thirdDevice;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Device{" +
                "Id=" + Id +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType=" + deviceType +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", villageId=" + villageId +
                ", buildingId=" + buildingId +
                ", unitId=" + unitId +
                ", roomId=" + roomId +
                ", deviceBelong='" + deviceBelong + '\'' +
                ", effectiveYear=" + effectiveYear +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", villageName='" + villageName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
