package cn.sparrow.permission.service.organization;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.model.SparrowTree;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.repository.organization.OrganizationRelationRepository;
import cn.sparrow.permission.service.AbstractTreeService;

@Service
public class OrganizationTreeServiceImpl extends AbstractTreeService<Organization, String> {

  @Autowired
  OrganizationRelationRepository organizationRelationRepository;

  @Override
  public SparrowTree<Organization, String> buildTree(String parentId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SparrowTree<Organization, String> buildTreeWithParent(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<SparrowTree<Organization, String>> getChildren(String parentId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isChild(String childId, String parentId) {
    return isChild$(childId, parentId);
  }

  @Override
  public boolean isAndChild(String childId, String parentId) {
    if (childId.equals(parentId) || isChild(childId, parentId))
      return true;
    return false;
  }

  @Override
  public boolean isParent(String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAndParent(String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isChildToParent(String id, String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAndChildToParent(String id, String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isChildToAndParent(String id, String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAndChildToAndParent(String id, String childId, String parentId) {
    // TODO Auto-generated method stub
    return false;
  }

  private boolean isChild$(String childId, String parentId) {
    boolean isChild = false;
    for (OrganizationRelation organizationRelation : organizationRelationRepository
        .findByIdParentId(parentId)) {
      if (organizationRelation.getId().getOrganizationId().equals(childId)) {
        return true;
      } else {
        isChild = isChild || isChild$(childId, organizationRelation.getId().getOrganizationId());
      }
    }
    return isChild;
  }

}
