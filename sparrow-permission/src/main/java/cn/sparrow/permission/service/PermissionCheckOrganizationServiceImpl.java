package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.common.service.TreeService;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.permission.token.PermissionExpression;
import cn.sparrow.organization.repository.OrganizationRelationRepository;

@Service
public class PermissionCheckOrganizationServiceImpl
    implements PermissionCheckService<Organization, String> {

  @Autowired
  TreeService<Organization, String> sparrowTreeService;

  @Autowired
  OrganizationRelationRepository organizationRelationRepository;

  @Override
  public boolean checkPermission(String id, PermissionExpression<Organization, String> permissionExpression,
      PermissionEnum permission) {

    // check employee org permission
    switch (permissionExpression.getExpression()) {
      case IS_AND_BELOW:

        return sparrowTreeService.isAndChild(id, (String) permissionExpression.getId());

      default:
        break;
    }

    // if(permissionExpression.getPermission().equals(permission) && permissionExpression)
    return false;
  }


}
