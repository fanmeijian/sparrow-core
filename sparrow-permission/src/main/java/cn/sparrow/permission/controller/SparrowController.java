package cn.sparrow.permission.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.model.AuditLog;
import cn.sparrow.permission.model.Model;
import cn.sparrow.permission.model.ModelAttribute;
import cn.sparrow.permission.model.ModelAttributePK;
import cn.sparrow.permission.repository.ApiRepository;
import cn.sparrow.permission.repository.AuditLogRepository;
import cn.sparrow.permission.repository.ModelAttributeRepository;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.service.SparrowService;

@RestController
public class SparrowController {


	@Autowired
	SparrowService sparrowService;

	

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ModelAttributeRepository modelAttributeRepository;

	@Autowired
	ApiRepository urlRepository;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired AuditLogRepository auditLogRepository;

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
