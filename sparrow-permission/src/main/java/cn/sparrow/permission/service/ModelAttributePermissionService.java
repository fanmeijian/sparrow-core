package cn.sparrow.permission.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.permission.AbstractModelAttributePermissionPK;
import cn.sparrow.model.permission.ModelAttributePermission;
import cn.sparrow.model.permission.SysroleModelAttributePermission;
import cn.sparrow.model.permission.UserModelAttributePermission;
import cn.sparrow.permission.repository.UserModelAttributePermissionRepository;

@Service
public class ModelAttributePermissionService extends AbstractPermissionService<AbstractModelAttributePermissionPK> {

	@Autowired UserModelAttributePermissionRepository userModelAttributePermissionRepository;
	
	@Override
	public boolean hasPermission(AbstractModelAttributePermissionPK target, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConfigPermission(AbstractModelAttributePermissionPK target,
			PermissionTargetEnum permissionTarget) {
		// TODO Auto-generated method stub
		return false;
	}

  @Override
  public boolean addPermission(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<AbstractModelAttributePermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }
  
  public void addPermissions(ModelAttributePermission modelAttributePermission) {
	  if(modelAttributePermission.getUserModelAttributePermissionPKs()!=null) {
		  modelAttributePermission.getUserModelAttributePermissionPKs().forEach(f->{
			  userModelAttributePermissionRepository.save(new UserModelAttributePermission(f));
		  });
	  }
	  
	  if(modelAttributePermission.getSysroleModelAttributePermissionPKs()!=null) {
		  modelAttributePermission.getSysroleModelAttributePermissionPKs().forEach(f->{
			  sysroleModelAttributePermissionRepository.save(new SysroleModelAttributePermission(f));
		  });
	  }
  }
  
  public void delPermissions(ModelAttributePermission modelAttributePermission) {
	  if(modelAttributePermission.getUserModelAttributePermissionPKs()!=null) {
		  userModelAttributePermissionRepository.deleteByIdIn(modelAttributePermission.getUserModelAttributePermissionPKs());
	  }
	  
	  if(modelAttributePermission.getSysroleModelAttributePermissionPKs()!=null) {
		  sysroleModelAttributePermissionRepository.deleteByIdIn(modelAttributePermission.getSysroleModelAttributePermissionPKs());
	  }
  }

}
