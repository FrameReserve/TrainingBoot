package com.training.core.dao.impl;

import com.training.core.annotation.MapperClass;
import com.training.core.helper.MyBatisHelper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import com.training.core.dao.BaseDao;
import com.training.core.dto.FlexiPageDto;
import com.training.core.entity.BaseEntity;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.util.List;

@Repository("myBatisBaseDao")
@SuppressWarnings("unchecked")
public class MyBatisBaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
	
	@Resource
	private MyBatisHelper myBatisHelper;
	
	@SuppressWarnings("rawtypes")
	public <M extends Mapper<T>> M getMapper(Class cls){
		MapperClass mapperClass = (MapperClass) cls.getAnnotation(MapperClass.class);
		if(null == mapperClass){
			throw new RuntimeException("没有注解MapperClass");
		}
		 	return (M)myBatisHelper.getMapper(mapperClass.value());
	}

	@Override
	public T getEntityById(Class<T> cls, Integer id) {
		return this.getMapper(cls).selectByPrimaryKey(id);
	}

	@Override
	public void addEntity(T entity) {
		this.getMapper(entity.getClass()).insert(entity);
	}

	@Override
	public void updateEntity(T entity) {
		this.getMapper(entity.getClass()).updateByPrimaryKey(entity);
	}

	@Override
	public void deleteEntityById(Class<T> cls, Integer id) {
		this.getMapper(cls).deleteByPrimaryKey(id);
	}

	@Override
	public List<T> selectAll(Class<T> cls) {
		return this.getMapper(cls).selectAll();
	}

	@Override
	public List<T> findByLike(Example example) {
		return this.getMapper(example.getEntityClass()).selectByExample(example);
	}

	@Override
	public List<T> findByPage(Example example, FlexiPageDto flexiPageDto) {
		
		RowBounds rowBounds = new RowBounds(flexiPageDto.getOffset(), flexiPageDto.getRp());
		return this.getMapper(example.getEntityClass()).selectByExampleAndRowBounds(example, rowBounds);
	}

	@Override
	public int findRowCount(Example example) {
		return this.getMapper(example.getEntityClass()).selectCountByExample(example);
	}
	
}
