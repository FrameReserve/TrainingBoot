package com.training.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericeClassUtils {

	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {

		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return null;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return null;
		}

		if (!(params[index] instanceof Class)) {
			return null;
		}
		return (Class) params[index];
	}
}
