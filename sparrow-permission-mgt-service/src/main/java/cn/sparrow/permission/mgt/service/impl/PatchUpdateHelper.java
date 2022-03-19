package cn.sparrow.permission.mgt.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PatchUpdateHelper {

	public static void merge(Object source, Map<String, Object> map) {
		try {
			System.out.println(new ObjectMapper().writeValueAsString(map));
			Object object = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(map), source.getClass());
			map.entrySet().forEach(f -> {
				Field field = ReflectionUtils.findField(source.getClass(), f.getKey());
				Method method = ReflectionUtils.findMethod(source.getClass(),
						"set" + StringUtils.capitalize(f.getKey()), field.getType());
				Method getMethod = ReflectionUtils
						.findMethod(object.getClass(),
						"get" + StringUtils.capitalize(f.getKey()));
				// jackson反序列化时读取不到Boolean值
				if(field.getType().equals(Boolean.class)) {
					ReflectionUtils.invokeMethod(method, source, f.getValue());
				}else {
					ReflectionUtils.invokeMethod(method, source, ReflectionUtils.invokeMethod(getMethod, object));
				}
			});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void merge(Object source, Map<String, Object> map, Class<?> class1) {
		try {
			System.out.println(new ObjectMapper().writeValueAsString(map));
			Object object = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(map), class1);
			map.entrySet().forEach(f -> {
				Field field = ReflectionUtils.findField(source.getClass(), f.getKey());
				Method method = ReflectionUtils.findMethod(source.getClass(),
						"set" + StringUtils.capitalize(f.getKey()), field.getType());
				Method getMethod = ReflectionUtils
						.findMethod(object.getClass(),
						"get" + StringUtils.capitalize(f.getKey()));
				ReflectionUtils.invokeMethod(method, source, ReflectionUtils.invokeMethod(getMethod, object));
			});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
