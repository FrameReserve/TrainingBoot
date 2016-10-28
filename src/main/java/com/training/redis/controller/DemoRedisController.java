package com.training.redis.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.controller.BaseRedisController;
import com.training.redis.entity.DemoRedis;
import com.training.redis.service.DemoRedisService;

@RestController
@RequestMapping(value="/demoRedis") 
public class DemoRedisController extends BaseRedisController<DemoRedis> {

	@Resource
	private DemoRedisService demoRedisService;
	
}
