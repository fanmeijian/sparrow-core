package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.permission.Sysrole;
import cn.sparrow.model.permission.User;
import cn.sparrow.model.permission.token.ApiPermissionToken;
import cn.sparrow.model.permission.token.PermissionExpression;

@Service
public class ApiPermissionService extends AbstractPermissionServiceV2<ApiPermissionToken> {
  
  @Override
  public boolean hasPermission(EmployeeToken employeeToken, ApiPermissionToken permissionToken,
      PermissionEnum permission) {

    for (PermissionExpression<Employee, String> permissionExpression : permissionToken
        .getEmployeePermissionExpressions()) {
      if (employeeCheckService.checkPermission(employeeToken.getEmployeeId(), permissionExpression,
          permission))
        return true;
    }
    
    for (PermissionExpression<User, String> permissionExpression : permissionToken
        .getUserPermissionExpressions()) {   
      for(String username: employeeToken.getUsernames()) {  
        if (employeeCheckService.checkPermission(username, permissionExpression,
            permission))
          return true;
      }
    }
    
    for (PermissionExpression<Sysrole, String> permissionExpression : permissionToken
        .getSysrolePermissionExpressions()) {   
      for(String sysroleId: employeeToken.getSysroleIds()) {  
        if (sysroleCheckService.checkPermission(sysroleId, permissionExpression,
            permission))
          return true;
      }
    }
    
    for (PermissionExpression<Group, String> permissionExpression : permissionToken
        .getGroupPermissionExpressions()) {   
      for(String groupId: employeeToken.getGroupIds()) {  
        if (groupCheckService.checkPermission(groupId, permissionExpression,
            permission))
          return true;
      }
    }
    
    for (PermissionExpression<Organization, String> permissionExpression : permissionToken
        .getOrganizationPermissionExpressions()) {   
      for(String organizationId: employeeToken.getGroupIds()) {  
        if (organizationCheckService.checkPermission(organizationId, permissionExpression,
            permission))
          return true;
      }
    }
    
    for (PermissionExpression<OrganizationRole, OrganizationRolePK> permissionExpression : permissionToken
        .getRolePermissionExpressions()) {   
      for(OrganizationRolePK roleId: employeeToken.getRoleIds()) {  
        if (roleCheckService.checkPermission(roleId, permissionExpression,
            permission))
          return true;
      }
    }
    
    for (PermissionExpression<OrganizationPositionLevel, OrganizationPositionLevelPK> permissionExpression : permissionToken
        .getPositionLevelPermissionExpressions()) {   
      for(OrganizationPositionLevelPK positionLevelId: employeeToken.getPositionLevelIds()) {  
        if (positionLevelCheckService.checkPermission(positionLevelId, permissionExpression,
            permission))
          return true;
      }
    }

    return false;
  }


}
