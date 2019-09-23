package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.annotation.Log;
import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.common.exception.FebsException;
import com.aoke.apartmentsystem.system.entity.Contract;
import com.aoke.apartmentsystem.system.entity.Template;
import com.aoke.apartmentsystem.system.entity.TemplateContent;
import com.aoke.apartmentsystem.system.service.IContractService;
import com.aoke.apartmentsystem.system.service.ITemplateContentService;
import com.aoke.apartmentsystem.system.service.ITemplateService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IContractService contractService;

    @Resource
    private ITemplateService templateService;

    @Resource
    private ITemplateContentService templateContentService;

    @GetMapping("/list")
    @RequiresPermissions(value = {"contract:view"})
    public FebsResponse contractList(Contract contract, QueryRequest request) {
        IPage<Contract> iPage = new Page<>(request.getPageNum(), request.getPageSize());
        Wrapper<Contract> wrapper = new QueryWrapper<>(contract);

        Map<String, Object> dataTable = getDataTable(contractService.pageMaps(iPage, wrapper));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("修改合同")
    @PostMapping("/update")
    @RequiresPermissions(value = {"contract:update"})
    public FebsResponse updateContract(@Valid Contract contract) throws FebsException {
        try {
            if (contract.getContractId() == null)
                throw new FebsException("合同编号为空");
            contract.setUpdateBy(getCurrentUser().getUsername());
            contract.setUpdateTime(new Date());
            this.contractService.updateById(contract);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改合同失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("/template/list")
    @RequiresPermissions(value = {"template:view"})
    public FebsResponse templateList(Template template, QueryRequest request) {
        IPage<Template> iPage = new Page<>(request.getPageNum(), request.getPageSize());
        Wrapper<Template> wrapper = new QueryWrapper<>(template);

        Map<String, Object> dataTable = getDataTable(templateService.pageMaps(iPage, wrapper));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("/templateContent/list/{templateId}")
    public FebsResponse templateContentList(@PathVariable String templateId, QueryRequest request) {
        TemplateContent content = new TemplateContent();
        content.setTemplateId(templateId);

        IPage<TemplateContent> iPage = new Page<>(request.getPageNum(), request.getPageSize());
        Wrapper<TemplateContent> wrapper = new QueryWrapper<>(content);

        Map<String, Object> dataTable = getDataTable(templateContentService.pageMaps(iPage, wrapper));
        return new FebsResponse().success().data(dataTable);
    }

}
