package cn.sparrow.permission.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;
import cn.sparrow.permission.repository.organization.RoleRepository;
import cn.sparrow.permission.service.organization.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepository;


	@GetMapping("/roles/getChildren")
	public List<OrganizationRoleRelation> getChildren(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("roleId") final String roleId) {
		return roleService.getChildren(new OrganizationRolePK(organizationId, roleId));
	}

	@GetMapping("/roles/getParents")
	public List<OrganizationRoleRelation> getParents(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("roleId") final String roleId) {
		return roleService.getParents(new OrganizationRolePK(organizationId, roleId));
	}
	
	@GetMapping("/roles/getParentOrganizations")
	public List<Organization> getParentOrganizations(@NotBlank @RequestParam("roleId") final String roleId){
		return roleService.getParentOrganizations(roleId);
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
}
