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

import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;
import cn.sparrow.model.permission.ModelPermission;
import cn.sparrow.model.permission.SysroleModelPermission;
import cn.sparrow.model.permission.UserModelPermission;
import cn.sparrow.permission.repository.GroupModelPermissionRepository;
import cn.sparrow.permission.repository.ModelAttributeRepository;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.repository.OrganizationModelPermissionRepository;
import cn.sparrow.permission.repository.SysroleModelPermissionRepository;
import cn.sparrow.permission.repository.UserModelPermissionRepository;

@Service
public class ModelService {

  @Autowired
  ModelRepository modelRepository;
  
  @Autowired UserModelPermissionRepository userModelPermissionRepository;
  @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
  @Autowired GroupModelPermissionRepository groupModelPermissionRepository;
  @Autowired OrganizationModelPermissionRepository organizationModelPermissionRepository;
  @PersistenceContext
  EntityManager entityManager;
  @Autowired
  ModelAttributeRepository modelAttributeRepository;
  
  public Model getModel(Object object) {
    return modelRepository.findById(object.getClass().getName()).orElse(null);
  }
  
  public void addPermissions(ModelPermission permission) {
	  if(permission.getUserModelPermissionPKs()!=null) {
		  permission.getUserModelPermissionPKs().forEach(f->{
			  userModelPermissionRepository.save(new UserModelPermission(f));
		  });
	  }
	  
	  if(permission.getSysroleModelPermissionPKs()!=null) {
		  permission.getSysroleModelPermissionPKs().forEach(f->{
			  sysroleModelPermissionRepository.save(new SysroleModelPermission(f));
		  });
	  }
  }
  
  public void delPermissions(ModelPermission permission) {
	  if(permission.getUserModelPermissionPKs()!=null) {
		  userModelPermissionRepository.deleteByIdIn(permission.getUserModelPermissionPKs());
	  }
	  if(permission.getSysroleModelPermissionPKs()!=null) {
		  sysroleModelPermissionRepository.deleteByIdIn(permission.getSysroleModelPermissionPKs());
	  }
  }
  
  public void init() {
    Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
    Map<String, List<String>> entitiesMap = new HashMap<String, List<String>>();



    entityTypes.forEach(e -> {
      Model model = modelRepository.save(new Model(e.getJavaType().getName()));

      List<String> attributes = new ArrayList<String>();
      e.getAttributes().forEach(a -> {
        attributes.add(a.getName());

        modelAttributeRepository.save(new ModelAttribute(
            new ModelAttributePK(a.getName(), model.getName()), a.getJavaType().getName()));

      });
      entitiesMap.put(e.getJavaType().getName(), attributes);

    });
  }
  
}
