package cn.sparrow.permission.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;
import cn.sparrow.model.permission.ModelPermission;
import cn.sparrow.permission.repository.ModelAttributeRepository;
import cn.sparrow.permission.repository.ModelRepository;

@Service
public class ModelService {

	@Autowired
	ModelRepository modelRepository;

//  @Autowired UserModelPermissionRepository userModelPermissionRepository;
//  @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
//  @Autowired OrganizationModelPermissionRepository organizationModelPermissionRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	ModelAttributeRepository modelAttributeRepository;

	public Model getModel(Object object) {
		return modelRepository.findById(object.getClass().getName()).orElse(null);
	}

	public void addPermissions(ModelPermission modelPermission) {
		modelPermission.getModelName().forEach(modelName -> {
			Model model = modelRepository.findById(modelName).get();
			model.setModelPermissionToken(modelPermission.getPermissionToken());
			model.setModelPermissionTokenByteArray(SerializationUtils.serialize(modelPermission.getPermissionToken()));
			modelRepository.save(model);
		});
	}

	public void delPermissions(ModelPermission modelPermission) {

	}

	public void init() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		Map<String, List<String>> entitiesMap = new HashMap<String, List<String>>();

		entityTypes.forEach(e -> {
			Model model = modelRepository.save(new Model(e.getJavaType().getName(), true));

			List<String> attributes = new ArrayList<String>();
			e.getAttributes().forEach(a -> {
				attributes.add(a.getName());

				modelAttributeRepository.save(new ModelAttribute(new ModelAttributePK(a.getName(), model.getName()),
						a.getJavaType().getName()));

			});
			entitiesMap.put(e.getJavaType().getName(), attributes);

		});
	}

}
