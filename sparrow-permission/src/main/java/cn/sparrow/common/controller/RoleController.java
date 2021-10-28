package cn.sparrow.common.controller;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.organization.OrganizationRoleRelation;
import cn.sparrow.model.organization.Role;
import cn.sparrow.organization.repository.RoleRepository;
import cn.sparrow.organization.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/roles")
	public Role save(@NotNull @RequestBody Role role) {
		return roleService.save(role);
	}
	
	@GetMapping("/roles/getChildren")
	public List<OrganizationRoleRelation> getChildren(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("roleId") final String roleId){
	  return roleService.getChildren(new OrganizationRolePK(organizationId, roleId));
	}
	
	@GetMapping("/roles/getParents")
	public List<OrganizationRoleRelation> getParents(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("roleId") final String roleId){
	  return roleService.getParents(new OrganizationRolePK(organizationId, roleId));
	}

	@GetMapping("/roles/getModelName")
	public String getModelName() {
		return "{\"modelName\":\"" + Role.class.getName() + "\"}";
	}

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
		roleService.delBatch(ids);
	}

	@PostMapping("/roles/relations")
	public void addRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids) {
		roleService.addRelations(ids);
	}

	@DeleteMapping("/roles/relations")
	public void delRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids) {
		roleService.delRelations(ids);
	}
	

	@GetMapping("/roles/getTreeByParentId")
	public MyTree<Role> tree(@Nullable @RequestParam("parentId") String parentId) {
		return roleService.getTree(parentId == null || parentId.isBlank() ? null : parentId);
	}
}
