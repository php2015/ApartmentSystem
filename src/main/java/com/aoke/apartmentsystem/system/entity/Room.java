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
 * 房间
 * @author xiaoxinglin
 */
@Data
@TableName("t_room")
@Excel("房间信息表")
public class Room implements Serializable {

    // 房间状态：有效
    public static final int STATUS_VALID = 1;
    // 房间状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认房间图片
    public static final String DEFAULT_AVATAR = "default_room.jpg";
    // 默认房间名称
    public static final String DEFAULT_NAME = "room01";

    /**
     * 房间 ID
     */
    @TableId(value = "room_id", type = IdType.AUTO)
    private Long roomId;

    /**
     * 楼栋 ID
     */
    @TableId(value = "building_id")
    private Long buildingId;

    /**
     * 房间名称
     */
    @TableField("room_name")
    @Size(min = 2, max = 20, message = "{range}")
    @ExcelField(value = "房间名称")
    private String roomName;

    /**
     * 房间状态(全部、空置、已租、即将到期：10天内)
     */
    @TableField("room_status")
    private int status;

    /**
     * 户型(1一室、2一室一厅、3两室一厅、4三室一厅、5四室一厅、6其它
     */
    @TableField("room_type")
    private int roomType;

    /**
     * 房间面积
     */
    @TableField("room_area")
    private int roomArea;

    /**
     * 房间价格
     */
    @TableField("room_price")
    private Double roomPrice;

    /**
     * 结算方式(押一付三、押一付一、自定义：文字输入)
     */
    @TableField("pay_way")
    @ExcelField(value = "结算方式")
    private int payWay;

    /**
     * 配套：动态添加如热水器、空调等
     */
    @TableField("room_other")
    @ExcelField(value = "配套")
    private String roomOther;

    /**
     * 房间图片
     */
    @TableField("room_pic")
    private String roomPic;

    /**
     * 周边地图截图
     */
    @TableField("map_pic")
    @ExcelField(value = "周边地图截图)")
    private String mapPic;

    /**
     * 合同Id
     */
    @TableField("contract_id")
    @ExcelField(value = "合同Id")
    private String contractId;

    /**
     * 房间简介：250字节以内
     */
    @TableField("room_msg")
    @ExcelField(value = "房间简介")
    private String roomMsg;

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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getRoomOther() {
        return roomOther;
    }

    public void setRoomOther(String roomOther) {
        this.roomOther = roomOther;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic;
    }

    public String getMapPic() {
        return mapPic;
    }

    public void setMapPic(String mapPic) {
        this.mapPic = mapPic;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRoomMsg() {
        return roomMsg;
    }

    public void setRoomMsg(String roomMsg) {
        this.roomMsg = roomMsg;
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
        return "Room{" +
                "roomId=" + roomId +
                ", buildingId=" + buildingId +
                ", roomName='" + roomName + '\'' +
                ", status=" + status +
                ", roomType=" + roomType +
                ", roomArea=" + roomArea +
                ", roomPrice=" + roomPrice +
                ", payWay=" + payWay +
                ", roomOther='" + roomOther + '\'' +
                ", roomPic='" + roomPic + '\'' +
                ", mapPic='" + mapPic + '\'' +
                ", contractId='" + contractId + '\'' +
                ", roomMsg='" + roomMsg + '\'' +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
