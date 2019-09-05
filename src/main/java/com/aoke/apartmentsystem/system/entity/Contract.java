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
 * 合同
 * @author xiaoxinglin
 */
@Data
@TableName("t_contract")
@Excel("合同信息表")
public class Contract implements Serializable {

    // 合同状态：有效
    public static final int STATUS_VALID = 1;
    // 合同状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认合同名称
    public static final String DEFAULT_NAME = "contract01";

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long Id;

    /**
     * 合同 ID
     */
    @TableId(value = "contract_id")
    private String contractId;

    /**
     * 租户姓名
     */
    @TableId(value = "tenant_name")
    private String tenantName;

    /**
     * 乙方证件类型
     */
    @TableField("credentials_type")
    @ExcelField(value = "乙方证件类型")
    private int credentialsType;

    /**
     * 乙方证件号
     */
    @TableField("credentials_code")
    @Size(min = 4, max = 50, message = "{range}")
    @ExcelField(value = "乙方证件号")
    private String credentialsCode;

    /**
     * 乙方联系电话
     */
    @TableField("phone")
    @ExcelField(value = "乙方联系电话")
    private String phone;

    /**
     * 市/区/小区楼栋单元房间
     */
    @TableField("village_address")
    @Size(min = 4, max = 50, message = "{range}")
    @ExcelField(value = "地址")
    private String villageAddress;

    /**
     * 房屋面积
     */
    @TableField("area")
    @ExcelField(value = "房屋面积")
    private int area;

    /**
     * 合同周期(月为单位的整型)
     */
    @TableField("contract_cycle")
    private int contractCycle;

    /**
     * 起效日期(按租户签字时间年-月-日)
     */
    @TableField("effective_time")
    @ExcelField(value = "起效日期", writeConverter = TimeConverter.class)
    private Date effective_time;

    /**
     * 租金
     */
    @TableField("tenant_price")
    private Double tenantPrice;

    /**
     * 支付周期
     */
    @TableField("pay_time")
    @ExcelField(value = "支付周期", writeConverter = TimeConverter.class)
    private Date payTime;

    /**
     * 状态（有效、实现、到期、待审核、驳回、退租中、退组、未付款、续租）
     */
    @TableField("contract_status")
    @ExcelField(value = "状态")
    private int contractStatus;

    /**
     * 甲方证件类型
     */
    @TableField("host_credentials_type")
    @ExcelField(value = "甲方证件类型")
    private int hostCredentialsType;

    /**
     * 甲方证件号
     */
    @TableField("host_credentials_code")
    @ExcelField(value = "甲方证件号")
    private String hostCredentialsCode;

    /**
     * 甲方联系电话
     */
    @TableField("host_phone")
    @ExcelField(value = "甲方联系电话")
    private String hostPhone;

    /**
     * 合同内容3000字节以内
     */
    @TableField("contract_connet")
    @ExcelField(value = "合同内容")
    private String contractConnet;

    /**
     * 附件(如公章、签名)
     */
    @TableField("adjunct")
    @ExcelField(value = "附件")
    private String adjunct;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public int getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(int credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsCode() {
        return credentialsCode;
    }

    public void setCredentialsCode(String credentialsCode) {
        this.credentialsCode = credentialsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVillageAddress() {
        return villageAddress;
    }

    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getContractCycle() {
        return contractCycle;
    }

    public void setContractCycle(int contractCycle) {
        this.contractCycle = contractCycle;
    }

    public Date getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(Date effective_time) {
        this.effective_time = effective_time;
    }

    public Double getTenantPrice() {
        return tenantPrice;
    }

    public void setTenantPrice(Double tenantPrice) {
        this.tenantPrice = tenantPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public int getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(int contractStatus) {
        this.contractStatus = contractStatus;
    }

    public int getHostCredentialsType() {
        return hostCredentialsType;
    }

    public void setHostCredentialsType(int hostCredentialsType) {
        this.hostCredentialsType = hostCredentialsType;
    }

    public String getHostCredentialsCode() {
        return hostCredentialsCode;
    }

    public void setHostCredentialsCode(String hostCredentialsCode) {
        this.hostCredentialsCode = hostCredentialsCode;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    public String getContractConnet() {
        return contractConnet;
    }

    public void setContractConnet(String contractConnet) {
        this.contractConnet = contractConnet;
    }

    public String getAdjunct() {
        return adjunct;
    }

    public void setAdjunct(String adjunct) {
        this.adjunct = adjunct;
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
        return "Contract{" +
                "Id=" + Id +
                ", contractId='" + contractId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", credentialsType=" + credentialsType +
                ", credentialsCode='" + credentialsCode + '\'' +
                ", phone='" + phone + '\'' +
                ", villageAddress='" + villageAddress + '\'' +
                ", area=" + area +
                ", contractCycle=" + contractCycle +
                ", effective_time=" + effective_time +
                ", tenantPrice=" + tenantPrice +
                ", payTime=" + payTime +
                ", contractStatus=" + contractStatus +
                ", hostCredentialsType=" + hostCredentialsType +
                ", hostCredentialsCode='" + hostCredentialsCode + '\'' +
                ", hostPhone='" + hostPhone + '\'' +
                ", contractConnet='" + contractConnet + '\'' +
                ", adjunct='" + adjunct + '\'' +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
