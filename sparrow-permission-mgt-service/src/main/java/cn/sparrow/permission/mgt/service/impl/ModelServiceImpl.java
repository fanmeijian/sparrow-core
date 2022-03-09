package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.ModelService;
import cn.sparrow.permission.mgt.service.repository.ModelAttributeRepository;
import cn.sparrow.permission.mgt.service.repository.ModelRepository;
import cn.sparrow.permission.mgt.service.repository.PermissionTokenRepository;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;
import cn.sparrow.permission.model.resource.ModelPermission;
import cn.sparrow.permission.model.token.SparrowPermissionToken;

@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	ModelRepository modelRepository;

	// @Autowired UserModelPermissionRepository userModelPermissionRepository;
	// @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
	// @Autowired OrganizationModelPermissionRepository
	// organizationModelPermissionRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	ModelAttributeRepository modelAttributeRepository;
	@Autowired
	PermissionTokenRepository permissionTokenRepository;

	@Override
	public Model getModel(String modelId) {
		return modelRepository.findById(modelId).orElse(null);
	}

	@Override
	@Transactional
	public void addPermission(ModelPermission modelPermission) {
		modelPermission.getModelName().forEach(modelName -> {
			Model model = modelRepository.findById(modelName).get();
			SparrowPermissionToken sparrowPermissionToken = model.getSparrowPermissionToken();
			if (sparrowPermissionToken == null) {
				sparrowPermissionToken = new SparrowPermissionToken(modelPermission.getPermissionToken());
				model.setSparrowPermissionToken(permissionTokenRepository.save(sparrowPermissionToken));
				modelRepository.save(model);
			} else {
				sparrowPermissionToken.setPermissionToken(modelPermission.getPermissionToken());
				permissionTokenRepository.save(sparrowPermissionToken);
			}
		});
	}

	@Override
	@Transactional
	public void removePermission(ModelPermission modelPermission) {

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

	@Override
	public Page<Model> models(Pageable pageable, Model model) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return modelRepository.findAll(Example.of(model, matcher), pageable);
	}

	@Override
	public Model create(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public Model update(String modelId, Map<String, Object> map) {
		Model source = modelRepository.getById(modelId);
		PatchUpdateHelper.merge(source, map);
		return modelRepository.save(source);
	}

	@Override
	public void delete(List<String> ids) {
		modelRepository.deleteAllByIdInBatch(ids);
	}

}
