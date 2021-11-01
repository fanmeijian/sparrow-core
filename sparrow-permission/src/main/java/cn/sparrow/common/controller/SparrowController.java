package cn.sparrow.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.validation.constraints.NotEmpty;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.marshaller.optimized.OptimizedMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sun.istack.NotNull;

import cn.sparrow.common.repository.AuditLogRepository;
import cn.sparrow.common.service.SparrowService;
import cn.sparrow.common.service.UserService;
import cn.sparrow.model.common.AuditLog;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.model.permission.Menu;
import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;
import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.permission.repository.ModelAttributeRepository;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.repository.UrlRepository;
import cn.sparrow.permission.service.MenuService;
import cn.sparrow.permission.service.SysroleService;

@RestController
public class SparrowController {


	@Autowired
	SparrowService sparrowService;

	

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ModelAttributeRepository modelAttributeRepository;

	@Autowired
	UrlRepository urlRepository;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired AuditLogRepository auditLogRepository;
	@Autowired OptimizedMarshaller marsh;

	@GetMapping("/init")
	public void init() {
		sparrowService.init(null);
	}


	

	
	@GetMapping("/logs/auditLog")
	public Page<AuditLog> getAuditLog(Pageable pageable){
		Page<AuditLog> audits= auditLogRepository.findAll(pageable);
		audits.forEach(f->{
			f.setSourceObject(SerializationUtils.deserialize(f.getObjectBytearray()));
		});
		return audits;
	}
	

	@GetMapping("/models/syncToTable")
	public Map<String, List<String>> syncToTable() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		Map<String, List<String>> entitiesMap = new HashMap<String, List<String>>();

		entityTypes.forEach(e -> {
			Model model = modelRepository.save(new Model(e.getJavaType().getName()));

			List<String> attributes = new ArrayList<String>();
			e.getAttributes().forEach(a -> {
				attributes.add(a.getName());

				modelAttributeRepository.save(new ModelAttribute(new ModelAttributePK(a.getName(), model.getName()),
						a.getJavaType().getName()));

			});
			entitiesMap.put(e.getJavaType().getName(), attributes);

		});
		return entitiesMap;

	}

	@GetMapping("/models/allEntities")
	public Map<String, Object> allEntities() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Table.class);
		return beans;
	}

}
