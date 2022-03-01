package cn.sparrow.permission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.repository.organization.OrganizationRelationRepository;
import cn.sparrow.permission.service.TreeService;

@RestController
public class TreeController {
  @Autowired
  TreeService<Organization, String> sparrowTreeService;
  @Autowired
  OrganizationRelationRepository organizationRelationRepository;

  @GetMapping("/trees/organization/isAndChild")
  public boolean isAndChild(@RequestParam("id") String id,
      @RequestParam("parentId") String parentId) {

    return sparrowTreeService.isAndChild(id, parentId);

  }
}
