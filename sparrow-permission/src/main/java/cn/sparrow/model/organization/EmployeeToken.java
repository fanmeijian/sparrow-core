package cn.sparrow.model.organization;

import java.util.List;
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
