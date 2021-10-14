package cn.sparrow.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.organization.service.OrganizationService;

@RestController
public class EmployeeController {
  
  @Autowired OrganizationService organizationService;
  
  @GetMapping("/employees/getTreeByParentId")
  public MyTree<Organization> tree(@Nullable @RequestParam("parentId") String parentId){
    return organizationService.getTree(parentId);
  }
}
