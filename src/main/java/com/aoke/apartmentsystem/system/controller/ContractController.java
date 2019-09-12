package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import com.aoke.apartmentsystem.common.entity.QueryRequest;
import com.aoke.apartmentsystem.system.entity.Contract;
import com.aoke.apartmentsystem.system.service.IContractService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IContractService contractService;

    @GetMapping("/list")
    @RequiresPermissions(value = {"contract:view"})
    public FebsResponse contractList(Contract contract, QueryRequest request) {
        IPage<Contract> iPage = new Page<>(request.getPageNum(), request.getPageSize());
        Wrapper<Contract> wrapper = new QueryWrapper<>(contract);

        Map<String, Object> dataTable = getDataTable(contractService.pageMaps(iPage, wrapper));
        return new FebsResponse().success().data(dataTable);
    }

}
