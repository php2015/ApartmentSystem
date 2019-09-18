package com.aoke.apartmentsystem.system.entity;

import com.aoke.apartmentsystem.common.annotation.IsMobile;
import com.aoke.apartmentsystem.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_contract")
@Excel("合同信息表")
public class Contract implements Serializable {

    private static final long serialVersionUID = -3583635836267397636L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 合同编号
     */
    @TableId(value = "contract_id")
    @ExcelField(value = "合同编号")
    private String contractId;

    /**
     * 租户姓名
     */
    @TableField("tenant_name")
    @Size(max = 20)
    @ExcelField(value = "租户姓名")
    private String tenantName;

    /**
     * 乙方证件类型
     */
    @TableField("credentials_type")
    @ExcelField(value = "证件类型", writeConverterExp = "0=居民身份证,1=护照")
    private Integer credentialsType;

    /**
     * 乙方证件号码
     */
    @TableField("credentials_code")
    @Size(max = 50)
    @ExcelField(value = "证件号码")
    private String credentialsCode;

    /**
     * 乙方联系电话
     */
    @TableField("phone")
    @IsMobile(message = "{mobile}")
    private String phone;

    /**
     * 市/区/小区楼栋单元房间
     */
    @TableField("village_address")
    @Size(max = 50)
    private String villageAddress;

    /**
     * 房屋面积
     */
    @TableField("area")
    private Integer area;

    /**
     * 合同周期(月为单位的整型)
     */
    @TableField("contract_cycle")
    @ExcelField(value = "合同周期")
    private Integer contractCycle;

    /**
     * 起效日期(按租户签字时间年-月-日)
     */
    @TableField("effective_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelField(value = "起效日期", writeConverter = TimeConverter.class)
    private Date effectiveTime;

    /**
     * 租金
     */
    @TableField("tenant_price")
    @ExcelField(value = "租金")
    private BigDecimal tenantPrice;

    /**
     * 支付周期
     */
    @TableField("pay_time")
    private Integer payTime;

    /**
     * 状态（有效、实现、到期、待审核、驳回、退租中、退组、未付款、续租）
     */
    @TableField("contract_status")
    @ExcelField(value = "合同状态", writeConverterExp = "0=有效,1=实现,2=到期,3=待审核,4=驳回,5=退租中,6=退租,7=未付款,8=续租")
    private Integer contractStatus;

    /**
     * 甲方证件类型
     */
    @TableField("host_credentials_type")
    private Integer hostCredentialsType;

    /**
     * 甲方证件号码
     */
    @TableField("host_credentials_code")
    @Size(max = 50)
    private String hostCredentialsCode;

    /**
     * 甲方联系方式
     */
    @TableField("host_phone")
    @IsMobile(message = "{mobile}")
    private String hostPhone;

    /**
     * 合同内容
     */
    @TableField("contract_content")
    private String contractContent;

    /**
     * 附件（如公章、签名）
     */
    @TableField("adjunct")
    @Size(max = 100)
    private String adjunct;

    /**
     * 备注
     */
    @TableField("remark")
    @Size(max = 50)
    private String remark;

    /**
     * 创建者
     */
    @TableField("create_by")
    @Size(max = 15)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    @Size(max = 15)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标志
     */
    @TableField("del_flag")
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getContractCycle() {
        return contractCycle;
    }

    public void setContractCycle(Integer contractCycle) {
        this.contractCycle = contractCycle;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public BigDecimal getTenantPrice() {
        return tenantPrice;
    }

    public void setTenantPrice(BigDecimal tenantPrice) {
        this.tenantPrice = tenantPrice;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Integer getHostCredentialsType() {
        return hostCredentialsType;
    }

    public void setHostCredentialsType(Integer hostCredentialsType) {
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

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}