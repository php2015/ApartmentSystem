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
 * 租户
 * @author xiaoxinglin
 */
@Data
@TableName("t_tenant")
@Excel("租户信息表")
public class Tenant implements Serializable {

    // 用户状态：有效
    public static final int STATUS_VALID = 1;
    // 用户状态：锁定
    public static final int STATUS_LOCK = 0;
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";
    // 性别男
    public static final int SEX_MALE = 0;
    // 性别女
    public static final int SEX_FEMALE = 1;
    // 性别保密
    public static final int SEX_UNKNOW = 2;

    /**
     * 租户 ID
     */
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Long tenantId;

    /**
     * 租户姓名
     */
    @TableField("tenant_name")
    @Size(min = 4, max = 20, message = "{range}")
    @ExcelField(value = "租户姓名")
    private String tenantName;

    /**
     * 租户账号
     */
    @TableField("tenant_acount")
    @Size(min = 4, max = 20, message = "{range}")
    @ExcelField(value = "租户账号")
    private String tenantAcount;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 证件类型(0身份证、1港澳证、2居住证、3军官证、4护照、5其它)
     */
    @TableField("credentials_type")
    private int credentialsType;

    /**
     * 证件号
     */
    @TableField("credentials_code")
    private String credentialsCode;

    /**
     * 租户头像
     */
    @TableField("tenant_pic")
    private String tenantPic;

    /**
     * 联系电话
     */
    @TableField("phone")
    @ExcelField(value = "联系电话")
    private String phone;

    /**
     * 合同编号
     */
    @TableField("contract_id")
    @ExcelField(value = "合同编号")
    private String contractId;

    /**
     * 状态(0正常、2延期缴费、3已退租)
     */
    @TableField("status")
    private int status;

    /**
     * 信用额度
     */
    @TableField("credit")
    @ExcelField(value = "信用额度")
    private String credit;

    /**
     * 备注
     */
    @TableField("remark")
    @ExcelField(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantAcount() {
        return tenantAcount;
    }

    public void setTenantAcount(String tenantAcount) {
        this.tenantAcount = tenantAcount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTenantPic() {
        return tenantPic;
    }

    public void setTenantPic(String tenantPic) {
        this.tenantPic = tenantPic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "tenantId=" + tenantId +
                ", tenantName='" + tenantName + '\'' +
                ", tenantAcount='" + tenantAcount + '\'' +
                ", password='" + password + '\'' +
                ", credentialsType=" + credentialsType +
                ", credentialsCode='" + credentialsCode + '\'' +
                ", tenantPic='" + tenantPic + '\'' +
                ", phone='" + phone + '\'' +
                ", contractId='" + contractId + '\'' +
                ", status=" + status +
                ", credit='" + credit + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
