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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class ModelServiceImpl implements ModelService{

	@Autowired
	ModelRepository modelRepository;

//  @Autowired UserModelPermissionRepository userModelPermissionRepository;
//  @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
//  @Autowired OrganizationModelPermissionRepository organizationModelPermissionRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	ModelAttributeRepository modelAttributeRepository;
	@Autowired
	PermissionTokenRepository permissionTokenRepository;

	public Model getModel(Object object) {
		return modelRepository.findById(object.getClass().getName()).orElse(null);
	}

	@Transactional
	public void addPermissions(ModelPermission modelPermission) {
		modelPermission.getModelName().forEach(modelName -> {
			Model model = modelRepository.findById(modelName).get();
			SparrowPermissionToken sparrowPermissionToken = model.getSparrowPermissionToken();
			if(sparrowPermissionToken==null) {
				sparrowPermissionToken = new SparrowPermissionToken(modelPermission.getPermissionToken());
				model.setSparrowPermissionToken(permissionTokenRepository.save(sparrowPermissionToken));
				modelRepository.save(model);
			}else {
				sparrowPermissionToken.setPermissionToken(modelPermission.getPermissionToken());
				permissionTokenRepository.save(sparrowPermissionToken);
			}
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

	@Override
	public Page<Model> models(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(@NotNull Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Model> getModelsInId(@NotNull String[] ids, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(@NotNull List<Model> models) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(@NotNull List<Model> models) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(@NotNull String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPermission(@NotNull ModelPermission modelPermission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delPermission(@NotNull ModelPermission modelPermission) {
		// TODO Auto-generated method stub
		
	}

}
