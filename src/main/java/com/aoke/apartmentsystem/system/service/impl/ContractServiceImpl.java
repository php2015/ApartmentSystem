package com.aoke.apartmentsystem.system.service.impl;

import com.aoke.apartmentsystem.system.entity.Contract;
import com.aoke.apartmentsystem.system.mapper.ContractMapper;
import com.aoke.apartmentsystem.system.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {
}
