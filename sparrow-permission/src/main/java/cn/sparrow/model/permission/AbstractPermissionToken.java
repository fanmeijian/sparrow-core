package cn.sparrow.model.permission;

import java.util.List;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.permission.token.PermissionExpression;
import lombok.Data;

@Data
public abstract class AbstractPermissionToken {
  protected List<PermissionExpression<Employee,String>> employeePermissionExpressions;
  protected List<PermissionExpression<User,String>> userPermissionExpressions;
  protected List<PermissionExpression<Sysrole,String>> sysrolePermissionExpressions;
  protected List<PermissionExpression<Group,String>> groupPermissionExpressions;
  protected List<PermissionExpression<Organization,String>> organizationPermissionExpressions;
  protected List<PermissionExpression<OrganizationRole,OrganizationRolePK>> rolePermissionExpressions;
  protected List<PermissionExpression<OrganizationPositionLevel,OrganizationPositionLevelPK>> positionLevelPermissionExpressions;
}
