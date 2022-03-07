package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;


public interface SparrowService {


	@GetMapping("/init")
	public void init(ConfigurableApplicationContext appContext);

//	@GetMapping("/logs/auditLog")
//	public Page<AuditLog> getAuditLog(Pageable pageable);
	

	@GetMapping("/models/syncToTable")
	public Map<String, List<String>> syncToTable() ;

	@GetMapping("/models/allEntities")
	public Map<String, Object> allEntities();

}
