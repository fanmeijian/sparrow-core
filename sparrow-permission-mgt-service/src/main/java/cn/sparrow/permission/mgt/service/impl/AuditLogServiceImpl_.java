package cn.sparrow.permission.mgt.service.impl;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.core.api.AuditLogService;
import cn.sparrow.permission.mgt.api.AuditLogRestService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class AuditLogServiceImpl_ implements AuditLogRestService {

	@Autowired
	AuditLogService auditLogService;
	static Class<?> keyClass = null;

	@Override
	public List<?> getLog(String className, String id) {

		try {

			ReflectionUtils.doWithLocalFields(Class.forName(className), field -> {
				if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
					keyClass = field.getType();
				}
			});
			try {
				if (keyClass.getName().equals(String.class.getName())) {
					return auditLogService.getLog(Class.forName(className), id.toString());
				} else {
					return auditLogService.getLog(Class.forName(className),
							new ObjectMapper().readValue(id.toString(), keyClass));
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}
