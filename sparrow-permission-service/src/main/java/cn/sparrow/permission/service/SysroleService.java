package cn.sparrow.permission.service;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sparrow.permission.model.UserSysrole;
import cn.sparrow.permission.model.UserSysrolePK;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/sysroles")
public interface SysroleService {
	
	@Operation(summary = "批量删除角色")
	@DeleteMapping("/sysroles/batch")
	public void delete(@NotNull @RequestBody String[] ids);

	@Operation(summary = "批量设置用户")
	@PostMapping("/sysroles/userSysroles")
	public void addPermissions(@NotNull @RequestBody List<UserSysrole> userSysroles);

	@Operation(summary = "批量移除职用户")
	@DeleteMapping("/sysroles/userSysroles")
	public void delPermissions(@NotNull @RequestBody List<UserSysrolePK> userSysrolePKs);
	
	@Operation(summary = "获取用户角色")
	@GetMapping("/sysroles/userSysroles")
	public List<UserSysrole> getUserSysroles(@NotBlank String username);

}
