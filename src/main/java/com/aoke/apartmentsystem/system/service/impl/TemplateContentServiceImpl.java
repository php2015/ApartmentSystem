package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.system.entity.TemplateContent;
import com.aoke.apartmentsystem.system.mapper.TemplateContentMapper;
import com.aoke.apartmentsystem.system.service.ITemplateContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TemplateContentServiceImpl extends ServiceImpl<TemplateContentMapper, TemplateContent> implements ITemplateContentService {
}
