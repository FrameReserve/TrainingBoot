package com.training.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.training.dubbo.dto.DubboDemoDto;
import com.training.dubbo.service.DubboDemoService;

public class DubboDemoServiceImpl implements DubboDemoService {

	@Override
	public DubboDemoDto getDubboDemoDto() {
		return new DubboDemoDto(1, "张三");
	}

	@Override
	public List<DubboDemoDto> findAllDubboDemoDto() {
		List<DubboDemoDto> list = new ArrayList<DubboDemoDto>();
		list.add(new DubboDemoDto(1, "张三"));
		list.add(new DubboDemoDto(2, "李四"));
		return list;
	}

}
