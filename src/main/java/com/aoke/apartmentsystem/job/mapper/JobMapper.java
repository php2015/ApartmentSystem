package com.aoke.apartmentsystem.job.mapper;


import com.aoke.apartmentsystem.job.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xiaoxinglin
 */
public interface JobMapper extends BaseMapper<Job> {
	
	List<Job> queryList();
}