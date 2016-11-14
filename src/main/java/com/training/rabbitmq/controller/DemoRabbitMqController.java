package com.training.rabbitmq.controller;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.dto.ResultDataDto;
import com.training.rabbitmq.sender.DemoRabbitMqSender;

/**
 * 测试Rabbit MQ
 */
@RestController
@RequestMapping(value="/rabbitmq")
public class DemoRabbitMqController {

	@Autowired
	private DemoRabbitMqSender demoRabbitMqSender;
	
	/**
	 * 发送测试消息队列
	 */
	@ApiOperation(value="发送测试消息队列", notes="addEntity")
    @RequestMapping(value = "/addRabbitMq", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto addEntity(HttpSession httpSession) {
		demoRabbitMqSender.send("jkljklkjljjkljl");
		return ResultDataDto.addAddSuccess();
	}
	
}
