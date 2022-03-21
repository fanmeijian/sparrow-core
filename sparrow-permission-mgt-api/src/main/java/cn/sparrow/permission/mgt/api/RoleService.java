package cn.sparrow.permission.mgt.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestBody;

import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;

public interface RoleService extends RoleRestService {

//	@Operation(summary = "获取下属岗位")
//	@GetMapping("/children")
//	@ResponseBody
	public List<OrganizationRoleRelation> getChildren(String organizationId, String roleId);

//	@Operation(summary = "获取上级岗位")
//	@GetMapping("/parents")
//	@ResponseBody
	public List<OrganizationRoleRelation> getParents(String organizationId, String roleId);

//	@Operation(summary = "设置岗位关系")
//	@PostMapping("/relation")
	public void addRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);

//	@Operation(summary = "移除岗位关系")
//	@DeleteMapping("/relation")
	public void delRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);

}
