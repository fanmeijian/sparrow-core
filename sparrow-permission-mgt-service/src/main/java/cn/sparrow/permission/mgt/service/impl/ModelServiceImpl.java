package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ModelService;
import cn.sparrow.permission.mgt.service.repository.ModelAttributeRepository;
import cn.sparrow.permission.mgt.service.repository.ModelRepository;
import cn.sparrow.permission.mgt.service.repository.PermissionTokenRepository;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.token.DataPermissionToken;
import cn.sparrow.permission.model.token.PermissionToken;
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

//	@Override
//	@Transactional
//	public void addPermissions(ModelPermission modelPermission) {
//		modelPermission.getModelName().forEach(modelName -> {
//			this.addPermission(modelName, modelPermission.getPermissionToken());
//		});
//	}

	@Override
	@Transactional
	public void removePermission(String modelId) {
		Model model = modelRepository.getById(modelId);
		model.setSparrowPermissionToken(null);
		modelRepository.save(model);
	}

	@Transactional
	@Override
	public void init() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		entityTypes.forEach(e -> {
			modelRepository.save(new Model(e.getJavaType().getName(), true));
			List<String> attributes = new ArrayList<String>();
			e.getAttributes().forEach(a -> {
				attributes.add(a.getName());

				modelAttributeRepository
						.save(new ModelAttribute(e.getJavaType().getName(), a.getName(), a.getJavaType().getName()));
			});

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

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	public SparrowPermissionToken addPermission(String modelId, PermissionToken permissionToken) {
		Model model = modelRepository.findById(modelId).get();
		SparrowPermissionToken sparrowPermissionToken = model.getSparrowPermissionToken();
		if (sparrowPermissionToken == null) {
			sparrowPermissionToken = new SparrowPermissionToken(permissionToken);
			model.setSparrowPermissionToken(permissionTokenRepository.save(sparrowPermissionToken));
			modelRepository.save(model);
		} else {
			sparrowPermissionToken.setPermissionToken(permissionToken);
			permissionTokenRepository.save(sparrowPermissionToken);
		}
		return sparrowPermissionToken;
	}

	@Override
	public List<Model> getAllEntities() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		List<Model> models = new ArrayList<Model>();
		entityTypes.forEach(e -> {
			Model model = new Model(e.getJavaType().getName());

			List<ModelAttribute> attributes = new ArrayList<ModelAttribute>();
			e.getAttributes().forEach(a -> {
				ModelAttribute modelAttribute = new ModelAttribute(e.getJavaType().getName(), a.getName(),
						a.getJavaType().getName());
				attributes.add(modelAttribute);
			});
			model.setModelAttributes(attributes);
			models.add(model);
		});
		return models;
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public SparrowPermissionToken addDataPermission(String modelId, String dataId, PermissionToken permissionToken) {
		SparrowPermissionToken sparrowPermissionToken = null;
		try {
			AbstractSparrowEntity abstractSparrowEntity = (AbstractSparrowEntity) entityManager
					.find(Class.forName(modelId), dataId);

			sparrowPermissionToken = permissionTokenRepository.save(new SparrowPermissionToken(permissionToken));

			DataPermissionToken dataPermissionToken = new DataPermissionToken(sparrowPermissionToken.getId());
			entityManager.persist(dataPermissionToken);

			abstractSparrowEntity.setDataPermissionTokenId(dataPermissionToken.getId());
			entityManager.persist(abstractSparrowEntity);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sparrowPermissionToken;
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeDataPermission(String modelId, String dataId) {
		try {
			AbstractSparrowEntity abstractSparrowEntity = (AbstractSparrowEntity) entityManager
					.find(Class.forName(modelId), dataId);
			abstractSparrowEntity.setDataPermissionTokenId(null);
			entityManager.persist(abstractSparrowEntity);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
