package com.training.core.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;

import com.training.core.dao.BaseDao;
import com.training.core.dto.FlexiPageDto;
import com.training.core.dto.ResultDataDto;
import com.training.core.entity.BaseEntity;
import com.training.core.util.FilterUtil;
import com.training.core.util.GenericeClassUtils;

public class BaseCrudController<T extends BaseEntity> extends BaseController<T> {

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) GenericeClassUtils.getSuperClassGenricType(this.getClass(), 0);
	
	@Resource(name = "myBatisBaseDao")
	protected BaseDao<T> baseDao;
	
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
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
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
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
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
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public @ResponseBody ResultDataDto deleteEntityById(@PathVariable(value = "id") final Integer id) {
		baseDao.deleteEntityById(entityClass, id);
		return ResultDataDto.addDeleteSuccess();
	}
	
	/**
	 * 查询所有实体
	 */
	@ApiOperation(value="查询所有实体", notes="findAllEntitys")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/findAllEntitys", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto findAllEntitys() {
		List list = baseDao.selectAll(entityClass);
		return new ResultDataDto(list);
	}
	
	/**
	 * 分页模糊查询
	 * 
	 * @param pageNumber 当前页
	 * @param pageSize 每页显示数
	 * @param orderBy 排序条件
	 * &orderBy=name_desc,id_asc
	 * 
	 * @param filter 模糊查询条件
	 * 
	 * 查询条件说明：&filter=
	 * id_L_3_EQ__,
	 * 
	 * 属性(id) _ 类型(Long) _ 值(3) _ EQ(精确查询) _ AND(默认And查询) _ ,
	 * 
	 * 类型：
	 * 		S(String.class), I(Integer.class), L(Long.class), B(Boolean.class), 
	 * 		C(Collection.class) 例：1,2,3,4,5。使用,间隔。
	 * 
	 * 匹配模式：
	 * 		IN      例： select * from tableName where id in (1,2,3,4,5);
	 * 		EQ,     例： select * from tableName where id = 1;
	 * 		LIKE,   例： select * from tableName where name like '%张三%';
	 * 		LT,     例： select * from tableName where id < 100;
	 * 		GT,     例： select * from tableName where id > 100;
	 * 		LE,     例： select * from tableName where id <= 100;
	 * 		GE      例： select * from tableName where id >= 100;
	 * 		
	 * 
	 * 完整案例：
	 * select * from tableName where id = 3 and name = '张三' limit 0, 10
	 * http://localhost:8080/syssetting/findLikePage?pageNumber=1&pageSize=10&orderBy=&filter=id_L_2_EQ__,name_S_资源_LIKE__&
	 * 
	 * OR案例：
	 * select * from tableName where (id = 3 or name = '张三') or name = '李四' limit 0, 10
	 * &filter=id_L_3_EQ_OR_,name_S_张三_EQ_OR_,name_S_李四_EQ_OR_
	 * 
	 * 
	 * 
	 */
	@ApiOperation(value="分页模糊查询", notes="findLikePage")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示数", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "orderBy", value = "排序：name_desc,id_asc", required = true, dataType = "String"),
        @ApiImplicitParam(name = "filter", value = "过滤规则：&filter=id_L_3_EQ_OR_,name_S_张三_EQ_OR_,name_S_李四_EQ_OR_", required = true, dataType = "String")
	})	
	@RequestMapping(value = "/findLikePage", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto findLikePage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy, @RequestParam("filter") String filterStr) {
		
		Example example = FilterUtil.parsePropertyFilterExp(entityClass, filterStr, orderBy);
		FlexiPageDto flexiPageDto = new FlexiPageDto(pageNumber, pageSize);
		List<T> list = this.baseDao.findByPage(example, flexiPageDto);
		return new ResultDataDto(list);
	}
	
	@ApiOperation(value="分页模糊查询总记录数", notes="findLikePageRowCount")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示数", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "orderBy", value = "排序：name_desc,id_asc", required = true, dataType = "String"),
        @ApiImplicitParam(name = "filter", value = "过滤规则：&filter=id_L_3_EQ_OR_,name_S_张三_EQ_OR_,name_S_李四_EQ_OR_", required = true, dataType = "String")
	})
	@RequestMapping(value = "/findLikePageRowCount", method = RequestMethod.GET)
	public @ResponseBody ResultDataDto findLikePageRowCount(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy, @RequestParam("filter") String filterStr) {
		
		Example example = FilterUtil.parsePropertyFilterExp(entityClass, filterStr, orderBy);
		Integer count = this.baseDao.findRowCount(example);
		return new ResultDataDto().setCode(ResultDataDto.CODE_SUCCESS).setDatas(count);
	}
	
}
