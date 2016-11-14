package com.training.email.controller;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.controller.BaseController;
import com.training.core.dto.ResultDataDto;
import com.training.core.email.EmailService;

@RestController
@RequestMapping(value="/email") 
public class DemoEmailController extends BaseController {

	@Resource
	private EmailService emailService;
	
	/**
	 * 测试邮件发送
	 */
	@ApiOperation(value="测试邮件发送", notes="getEntityById")
    @RequestMapping(value = "/getTestDemoEmail", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto getEntityById() throws Exception {
		String sendTo = "1265400024@qq.com";
		String titel = "测试邮件标题";
		String content = "测试邮件内容";
		emailService.sendSimpleMail(sendTo, titel, content);
		return ResultDataDto.addSuccess();
	}
}
