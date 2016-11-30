package com.training.dubbo.service;

import java.util.List;

import com.training.dubbo.dto.DubboDemoDto;

public interface DubboDemoService {

	public DubboDemoDto getDubboDemoDto();
	
	public List<DubboDemoDto> findAllDubboDemoDto();
	
}
