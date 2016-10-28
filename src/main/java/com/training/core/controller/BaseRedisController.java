package com.training.core.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.core.dao.impl.RedisBaseDaoImpl;
import com.training.core.dto.ResultDataDto;
import com.training.core.entity.BaseEntity;
import com.training.core.util.GenericeClassUtils;

public class BaseRedisController<T extends BaseEntity> extends BaseController<T> {

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) GenericeClassUtils.getSuperClassGenricType(this.getClass(), 0);
	
	@Resource(name = "redisBaseDao")
	protected RedisBaseDaoImpl<T> baseDao;
	
	/**
	 * 根据Id查询实体
	 */
	@ApiOperation(value="根据Id查询实体", notes="getEntityById")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/getEntityById/{id}", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto getEntityById(@PathVariable(value = "id") final Integer id) {
    	T entity = baseDao.getEntityById(entityClass, id);
		return new ResultDataDto(entity);
	}
	
	/**
	 * 新增实体
	 */
	@ApiOperation(value="新增实体", notes="addEntity")
	@ApiImplicitParam(name = "entity", value = "实体Json", required = true, dataType = "application/json")
    @RequestMapping(value = "/addEntity", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResultDataDto addEntity(@RequestBody final T entity) {
		baseDao.addEntity(entity);
		return ResultDataDto.addAddSuccess();
	}
	
	/**
	 * 更新实体
	 */
	@ApiOperation(value="更新实体", notes="updateEntity")
	@ApiImplicitParam(name = "entity", value = "实体Json", required = true, dataType = "application/json")
    @RequestMapping(value = "/updateEntity", method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody ResultDataDto updateEntity(@RequestBody final T entity) {
		baseDao.updateEntity(entity);
		return ResultDataDto.addUpdateSuccess();
	}
	
	/**
	 * 根据Id删除实体
	 */
	@ApiOperation(value="根据Id删除实体", notes="deleteEntityById")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
	@RequestMapping(value = "/deleteEntityById/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResultDataDto deleteEntityById(@PathVariable(value = "id") final Integer id) {
		baseDao.deleteEntityById(entityClass, id);
		return ResultDataDto.addDeleteSuccess();
	}
	
}
