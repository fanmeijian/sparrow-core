package cn.sparrow.common.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sun.istack.NotNull;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Role;
import cn.sparrow.model.organization.RoleRelationPK;
import cn.sparrow.model.permission.Menu;
import cn.sparrow.organization.repository.RoleRepository;
import cn.sparrow.organization.service.RoleService;

@RestController
public class RoleController {

  @Autowired RoleService roleService;
  @Autowired RoleRepository roleRepository;
  
  @PostMapping("/roles/batch")
  public void add(@NotNull @RequestBody final List<Role> roles) {
    roleRepository.saveAll(roles);
  }

  @PatchMapping("/roles/batch")
  public void update(@NotNull @RequestBody final List<Role> roles) {
    roleRepository.saveAll(roles);
  }

  @DeleteMapping("/roles/batch")
  public void delete(@NotNull @RequestBody final String[] ids) {
    roleRepository.deleteByIdIn(ids);
  }
  
  @PostMapping("/roles/relations")
  public void addRelations(@NotNull @RequestBody Set<RoleRelationPK> ids) {
    roleService.addRelations(ids);
  }
  
  @DeleteMapping("/roles/relations")
  public void delRelations(@NotNull @RequestBody Set<RoleRelationPK> ids) {
    roleService.delRelations(ids);
  }
  
  @GetMapping("/roles/getTreeByParentId")
  public MyTree<Role> tree(@Nullable @RequestParam("parentId") String parentId){
    return roleService.getTree(parentId);
  }
}
