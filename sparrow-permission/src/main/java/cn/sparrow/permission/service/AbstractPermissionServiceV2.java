package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.permission.SparrowFunctionPK;
import cn.sparrow.model.permission.Sysrole;
import cn.sparrow.model.permission.User;
import cn.sparrow.model.permission.token.ApiPermissionToken;
import cn.sparrow.model.permission.token.DataPermissionToken;
import cn.sparrow.model.permission.token.FuncPermissionToken;
import cn.sparrow.model.permission.token.ModelPermissionToken;

public abstract class AbstractPermissionServiceV2<T> implements PermissionService<T> {
  
  @Autowired PermissionCheckService<Employee, String> employeeCheckService;
  @Autowired PermissionCheckService<User, String> userCheckService;
  @Autowired PermissionCheckService<Sysrole, String> sysroleCheckService;
  @Autowired PermissionCheckService<Group, String> groupCheckService;
  @Autowired PermissionCheckService<Organization, String> organizationCheckService;
  @Autowired PermissionCheckService<OrganizationRole, OrganizationRolePK> roleCheckService;
  @Autowired PermissionCheckService<OrganizationPositionLevel, OrganizationPositionLevelPK> positionLevelCheckService;

  
  public EmployeeToken buildEmployeeToken(String username) {
    return null;

  }

  public ApiPermissionToken buildApiPermissionToken(String apiPath, HttpMethod method) {
    return null;

  }

  public FuncPermissionToken buildFuncPermissionToken(SparrowFunctionPK functionPK) {
    return null;

  }

  public ModelPermissionToken builModelPermissionToken(String modelName) {
    return null;

  }

  public DataPermissionToken builDataPermissionToken(Object dataId, String modelName) {
    return null;

  }
}
