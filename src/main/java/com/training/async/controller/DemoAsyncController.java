package com.training.async.controller;

import io.swagger.annotations.ApiOperation;

import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.async.service.DemoAsyncService;
import com.training.core.dto.ResultDataDto;

@RestController
@RequestMapping(value="/async") 
public class DemoAsyncController {

	@Resource
	private DemoAsyncService demoAsyncService;

	/**
	 * 测试异步方法调用顺序
	 */
	@ApiOperation(value="测试异步方法调用顺序", notes="getEntityById")
    @RequestMapping(value = "/getTestDemoAsync", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto getEntityById() throws Exception {
    	
		long start = System.currentTimeMillis();

		Future<String> task1 = demoAsyncService.doTaskOne();
		Future<String> task2 = demoAsyncService.doTaskTwo();
		Future<String> task3 = demoAsyncService.doTaskThree();

		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
		return ResultDataDto.addSuccess();
	}
}
