package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.sparrow.common.repository.UserSysroleRepository;
import cn.sparrow.permission.repository.GroupDataFieldPermissionRepository;
import cn.sparrow.permission.repository.GroupDataPermissionRepository;
import cn.sparrow.permission.repository.GroupModelAttributePermissionRepository;
import cn.sparrow.permission.repository.GroupModelPermissionRepository;
import cn.sparrow.permission.repository.OrganizationDataFieldPermissionRepository;
import cn.sparrow.permission.repository.OrganizationDataPermissionRepository;
import cn.sparrow.permission.repository.OrganizationModelAttributePermissionRepository;
import cn.sparrow.permission.repository.OrganizationModelPermissionRepository;
import cn.sparrow.permission.repository.SysroleDataFieldPermissionRepository;
import cn.sparrow.permission.repository.SysroleDataPermissionRepository;
import cn.sparrow.permission.repository.SysroleModelAttributePermissionRepository;
import cn.sparrow.permission.repository.SysroleModelPermissionRepository;
import cn.sparrow.permission.repository.UserDataFieldPermissionRepository;
import cn.sparrow.permission.repository.UserDataPermissionRepository;
import cn.sparrow.permission.repository.UserFilePermissionRepository;
import cn.sparrow.permission.repository.UserModelAttributePermissionRepository;
import cn.sparrow.permission.repository.UserModelPermissionRepository;

public abstract class AbstractPermissionService<T> implements IPermission<T> {
	@Autowired UserModelPermissionRepository userModelPermissionRepository;
	@Autowired UserModelAttributePermissionRepository userModelAttributePermissionRepository;
	@Autowired UserDataPermissionRepository userDataPermissionRepository;
	@Autowired UserDataFieldPermissionRepository userDataFieldPermissionRepository;
	
	@Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
	@Autowired SysroleModelAttributePermissionRepository sysroleModelAttributePermissionRepository;
	@Autowired SysroleDataPermissionRepository sysroleDataPermissionRepository;
	@Autowired SysroleDataFieldPermissionRepository sysroleDataFieldPermissionRepository;
	
	
	@Autowired GroupModelPermissionRepository groupModelPermissionRepository;
	@Autowired GroupModelAttributePermissionRepository groupModelAttributePermissionRepository;
	@Autowired GroupDataPermissionRepository groupDataPermissionRepository;
	@Autowired GroupDataFieldPermissionRepository groupDataFieldPermissionRepository;
	
	@Autowired OrganizationModelPermissionRepository OrganizationModelPermissionRepository;
	@Autowired OrganizationModelAttributePermissionRepository organizationModelAttributePermissionRepository;
	@Autowired OrganizationDataPermissionRepository organizationDataPermissionRepository;
	@Autowired OrganizationDataFieldPermissionRepository organizationDataFieldPermissionRepository;
	
	@Autowired UserSysroleRepository userSysroleRepository;
	
	@Autowired UserFilePermissionRepository userFilePermissionRepository;
	
}
