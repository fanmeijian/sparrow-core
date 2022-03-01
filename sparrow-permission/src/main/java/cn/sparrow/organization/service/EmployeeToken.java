package cn.sparrow.organization.service;

import java.util.List;

import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRolePK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeToken {

  private String employeeId;
  List<String> usernames; // the employee will have more than one login account
  private List<String> sysroleIds;
  private List<String> groupIds;
  private List<String> organizationIds;
  private List<OrganizationRolePK> roleIds;
  private List<OrganizationPositionLevelPK> positionLevelIds;

}
