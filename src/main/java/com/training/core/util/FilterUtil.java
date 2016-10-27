package com.training.core.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class FilterUtil {

	public enum MatchType {
		IN, EQ, LIKE, LT, GT, ISNULL, LE, GE;
	}

	/**
	 * 解析过滤条件列表字符串 字段名称_值类型_值_比较方式_联合方式_关联实体<br>
	 * 值类型分为：
	 * 
	 * <pre>
	 * S(String.class), I(Integer.class), L(Long.class), B(Boolean.class), C(Collection.class);
	 * </pre>
	 * 
	 * 比较方式分为：
	 * 
	 * <pre>
	 * IN, EQ, LIKE, LT, GT, LE, GE;
	 * </pre>
	 * 
	 * 联合方式分为：
	 * 
	 * <pre>
	 * AND,OR;
	 * 若是AND可以留空
	 * </pre>
	 * 
	 * 联合方式分为： 若关联实体不是集合而是自身属性，则可以留空
	 * 
	 * <br>
	 * 例如：name_S_ch_LIKE__<br>
	 * 注意：若参数类型为Boolean时，True必须传入True或true，False可以不设置。例：isSet_B__EQ or
	 * isSet_B_True_EQ
	 * 
	 * @param filterStr
	 *            name_S_ch_LIKE__
	 * @return 若传入参数为空则返回null
	 */
	public static Example parsePropertyFilterExp(Class<? extends Object> entityClass, String filterStr, String orderByStr) {
		
		if (StringUtils.isEmpty(filterStr)) {
			return new Example(entityClass);
		}

		Example example = new Example(entityClass);
		
		if (filterStr.length()-1 > filterStr.lastIndexOf(",")) {
			filterStr = filterStr + ",";
		}
		String expReg = "([^\\_]*)_([^\\_]*)_([^\\_]*)_(.*?)_(.*?),";

		AssertUtils.isTrue(filterStr.matches(expReg), "错误的格式，请调整如下例：id_L_3_EQ_OR");
		
		List<String[]> exps = StringUtil.matchAll(filterStr, expReg);
		for (String[] singleExp : exps) {
			
			String property = singleExp[1];
			String typeName = singleExp[2];
			String value = singleExp[3];
			String mt = singleExp[4];
			String andOr = singleExp[5];
			
			Criteria criteria = null != andOr && "OR".equals(andOr.toUpperCase().trim()) ? example.or() : example.createCriteria();
			
			if (mt.equals("EQ")) {
				criteria.andEqualTo(property, value);
			} else if (mt.equals("GE")) {
				criteria.andGreaterThanOrEqualTo(property, value);
			} else if (mt.equals("GT")) {
				criteria.andGreaterThan(property, value);
			} else if (mt.equals("IN")) {
//				criteria.andIn(property, values);
			} else if (mt.equals("LE")) {
				criteria.andLessThanOrEqualTo(property, value);
			} else if (mt.equals("LT")) {
				criteria.andLessThan(property, value);
			} else if (mt.equals("LIKE")) {
				criteria.andLike(property, value);
			} else if (mt.equals("ISNULL")) {
				criteria.andIsNull(property);
			}
			
		}
		
		if (null == orderByStr || "".equals(orderByStr.trim())) {
			return example;
		}
		
		if (orderByStr.length()-1 > orderByStr.lastIndexOf(",")) {
			orderByStr = orderByStr + ",";
		}
		expReg = "([^\\_]*)_([^\\_]*),";
		AssertUtils.isTrue(orderByStr.matches(expReg), "排序，错误的格式，请调整如下例：name_desc,id_asc");
		
		exps = StringUtil.matchAll(orderByStr, expReg);
		for (String[] singleExp : exps) {
			String property = singleExp[1];
			String sortOrder = singleExp[2];
			if ("".equals(sortOrder.trim()) || "ASC".equals(sortOrder.toUpperCase().trim())) {
				example.orderBy(property).asc();
			} else {
				example.orderBy(property).desc();
			}
		}
		
		return example;
	}

}
