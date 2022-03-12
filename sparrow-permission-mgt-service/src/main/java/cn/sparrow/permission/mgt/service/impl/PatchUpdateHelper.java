package cn.sparrow.permission.mgt.service.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class PatchUpdateHelper {

	public static void merge(Object source, Map<String, Object> map) {
		map.entrySet().forEach(f -> {
			Method method = ReflectionUtils.findMethod(source.getClass(), "set" + StringUtils.capitalize(f.getKey()),
					ReflectionUtils.findField(source.getClass(), f.getKey()).getType());
			ReflectionUtils.invokeMethod(method, source, f.getValue());
		});
	}

}
