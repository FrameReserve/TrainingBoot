package com.training.core.helper;

import org.apache.ibatis.session.SqlSession;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Athos on 2016-07-04.
 */
public interface MyBatisHelper {
    SqlSession getSqlSession();
    <T extends Mapper> T getMapper(Class<T> cls);
}
