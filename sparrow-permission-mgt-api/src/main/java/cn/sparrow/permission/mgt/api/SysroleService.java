package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.User;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "角色服务")
@RequestMapping("/sysroles")
public interface SysroleService {
	
	@Operation(summary = "角色列表")
	@GetMapping("")
	@ResponseBody
	public Page<Sysrole> all(Pageable pageable, Sysrole sysrole);

	@Operation(summary = "新增角色")
	@PostMapping("")
	@ResponseBody
	public Sysrole create( @RequestBody Sysrole sysrole);

	@Operation(summary = "更新角色")
	@PatchMapping("/{sysroleId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Sysrole.class)))
	public Sysrole update(@PathVariable("sysroleId") String sysroleId, @RequestBody Map<String,Object> map);

	@Operation(summary = "删除角色")
	@DeleteMapping("")
	@ResponseBody
	public void delete( @RequestBody List<String> ids);

	@Operation(summary = "授权用户")
	@PostMapping("/users")
	@ResponseBody
	public void addPermissions( @RequestBody List<UserSysrolePK> ids);

	@Operation(summary = "取消用户授权")
	@DeleteMapping("/users")
	@ResponseBody
	public void removePermissions( @RequestBody List<UserSysrolePK> ids);
	
	@Operation(summary = "角色用户列表")
	@GetMapping("/{sysroleId}/users")
	@ResponseBody
	public List<String> getUsers(@NotBlank String sysroleId);

}
