package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "auditlog", description = "审计日志服务")
@RequestMapping("/logs")
public interface AuditLogRestService {
	
	@Operation(summary = "获取日志")
	@PostMapping("/{modelId}")
	@ResponseBody
	public List<?> getLog(@PathVariable("modelId") String className,@RequestBody String id);
}
