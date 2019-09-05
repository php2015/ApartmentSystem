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
 * 小区
 * @author xiaoxinglin
 */
@Data
@TableName("t_village")
@Excel("小区信息表")
public class Village implements Serializable {

    // 小区状态：有效
    public static final int STATUS_VALID = 1;
    // 小区状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认小区图片
    public static final String DEFAULT_AVATAR = "default_village.jpg";
    // 默认小区名称
    public static final String DEFAULT_NAME = "village01";

    /**
     * 小区 ID
     */
    @TableId(value = "village_id", type = IdType.AUTO)
    private Long villageId;

    /**
     * 小区名称
     */
    @TableField("village_name")
    @Size(min = 2, max = 20, message = "{range}")
    @ExcelField(value = "小区名称")
    private String villageName;

    /**
     * 所在市/区、小区地址
     */
    @TableField("village_address")
    @Size(min = 4, max = 20, message = "{range}")
    @ExcelField(value = "所在市/区、小区地址")
    private String villageAddress;

    /**
     * 小区类型(0集中式、1分散式、2其它)
     */
    @TableField("village_type")
    private int villageType;

    /**
     * 楼栋数量
     */
    @TableField("building_num")
    private int buildingNum;

    /**
     * 房间数量
     */
    @TableField("room_num")
    private int roomNum;

    /**
     * 负责人
     */
    @TableField("charge")
    private String charge;

    /**
     * 负责人电话
     */
    @TableField("charge_phone")
    @ExcelField(value = "负责人电话")
    private String chargePhone;

    /**
     * 经度、纬度
     */
    @TableField("jw")
    @ExcelField(value = "经度、纬度")
    private String jw;

    /**
     * 负责人头像
     */
    @TableField("charge_pic")
    private String chargePic;

    /**
     * 小区图片(一张2M以下)
     */
    @TableField("village_pic")
    @ExcelField(value = "小区图片(一张2M以下)")
    private String villagePic;

    /**
     * 小区配套：动态添加10个以内，如XX医院、学校等(以逗号隔开)
     */
    @TableField("village_orther")
    @ExcelField(value = "小区配套：动态添加10个以内，如XX医院、学校等(以逗号隔开)")
    private String villageOrther;

    /**
     * 小区简介：250字节以内
     */
    @TableField("village_msg")
    @ExcelField(value = "小区简介：250字节以内")
    private String villageMsg;

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

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageAddress() {
        return villageAddress;
    }

    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress;
    }

    public int getVillageType() {
        return villageType;
    }

    public void setVillageType(int villageType) {
        this.villageType = villageType;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }

    public String getJw() {
        return jw;
    }

    public void setJw(String jw) {
        this.jw = jw;
    }

    public String getChargePic() {
        return chargePic;
    }

    public void setChargePic(String chargePic) {
        this.chargePic = chargePic;
    }

    public String getVillagePic() {
        return villagePic;
    }

    public void setVillagePic(String villagePic) {
        this.villagePic = villagePic;
    }

    public String getVillageOrther() {
        return villageOrther;
    }

    public void setVillageOrther(String villageOrther) {
        this.villageOrther = villageOrther;
    }

    public String getVillageMsg() {
        return villageMsg;
    }

    public void setVillageMsg(String villageMsg) {
        this.villageMsg = villageMsg;
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
        return "Village{" +
                "villageId=" + villageId +
                ", villageName='" + villageName + '\'' +
                ", villageAddress='" + villageAddress + '\'' +
                ", villageType=" + villageType +
                ", buildingNum=" + buildingNum +
                ", roomNum=" + roomNum +
                ", charge='" + charge + '\'' +
                ", chargePhone='" + chargePhone + '\'' +
                ", jw='" + jw + '\'' +
                ", chargePic='" + chargePic + '\'' +
                ", villagePic='" + villagePic + '\'' +
                ", villageOrther='" + villageOrther + '\'' +
                ", villageMsg='" + villageMsg + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
