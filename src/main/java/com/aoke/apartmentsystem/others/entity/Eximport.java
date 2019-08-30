package com.aoke.apartmentsystem.others.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.wuwenze.poi.validator.EmailValidator;
import lombok.Data;

import java.util.Date;

/**
 * 导入导出测试，Eximport = export + import
 *
 * @author xiaoxinglin
 */
@Data
@TableName("t_eximport")
@Excel("测试导入导出数据")
public class Eximport {

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 字段1
     */
    @ExcelField(value = "字段1", required = true, maxLength = 20,
            comment = "提示：必填，长度不能超过20个字符")
    private String field1;

    /**
     * 字段2
     */
    @ExcelField(value = "字段2", required = true, maxLength = 11, regularExp = "[0-9]+",
            regularExpMessage = "必须是数字", comment = "提示: 必填，只能填写数字，并且长度不能超过11位")
    private Integer field2;

    /**
     * 字段3
     */
    @ExcelField(value = "字段3", required = true, maxLength = 50,
            comment = "提示：必填，只能填写邮箱，长度不能超过50个字符", validator = EmailValidator.class)
    private String field3;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Integer getField2() {
        return field2;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}