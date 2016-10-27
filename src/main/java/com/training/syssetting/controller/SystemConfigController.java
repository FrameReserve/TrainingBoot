package com.training.syssetting.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.controller.BaseCrudController;
import com.training.core.dto.ResultDataDto;
import com.training.syssetting.entity.SystemConfig;
import com.training.syssetting.service.SystemConfigService;

@RestController
@RequestMapping("/syssetting")
public class SystemConfigController extends BaseCrudController<SystemConfig> {

	@Resource
	private SystemConfigService systemConfigService;
	
}
