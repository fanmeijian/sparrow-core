package cn.sparrow.permission.mgt.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.sparrow.permission.mgt.api.PreserveScope;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractPreserveScope implements PreserveScope {

	@Override
	public List<String> getScopes() {
		List<String> fields = new ArrayList<>();
		for (Field field : this.getClass().getFields()) {
			log.debug("field name: {}", field.getName());
			if (field.getName().startsWith("SCOPE_"))
				try {
					fields.add(field.get(this).toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		return fields;
	}
	
	@Override
	public List<String> getRoles() {
		List<String> fields = new ArrayList<>();
		for (Field field : this.getClass().getFields()) {
			log.debug("field name: {}", field.getName());
			if (field.getName().startsWith("ROLE_"))
				try {
					fields.add(field.get(this).toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		return fields;
	}

}
