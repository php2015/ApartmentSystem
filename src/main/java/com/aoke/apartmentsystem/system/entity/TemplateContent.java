package com.aoke.apartmentsystem.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@TableName("t_template_content")
public class TemplateContent implements Serializable {

    private static final long serialVersionUID = -6389544940127137582L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板编号
     */
    @TableId(value = "template_id")
    private String templateId;

    /**
     * 内容字段名称
     */
    @TableField("content_key")
    @Size(max = 20)
    private String contentKey;

    /**
     * 内容字段值
     */
    @TableField("content_value")
    @Size(max = 255)
    private String contentValue;

    /**
     * 是否必填项
     */
    @TableField("content_required")
    private Integer contentRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public Integer getContentRequired() {
        return contentRequired;
    }

    public void setContentRequired(Integer contentRequired) {
        this.contentRequired = contentRequired;
    }
}
