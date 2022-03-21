package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.Scope;
import cn.sparrow.permission.model.resource.SysroleScope;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "opscope", description = "功能服务")
@RequestMapping("/opscopes")
public interface ScopeRestService {
	@PostMapping("")
	@Operation(summary = "新增功能")
	@ResponseBody
	public Scope create(@RequestBody Scope sparrowApi);

	@PatchMapping("/{scopeId}")
	@Operation(summary = "更新功能")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Scope.class)))
	@ResponseBody
	public Scope update(@PathVariable("scopeId") String scopeId, @RequestBody Map<String, Object> map);

	@GetMapping("/{scopeId}")
	@Operation(summary = "获取功能详情")
	@ResponseBody
	public Scope get(@PathVariable("scopeId") String id);

	@PutMapping("/delete")
	@Operation(summary = "删除功能")
	@ResponseBody
	public void delete(@RequestBody List<String> ids);

	@GetMapping("")
	@Operation(summary = "浏览功能")
	@ResponseBody
	public Page<Scope> all(@Nullable Pageable pageable, @Nullable Scope scope);

	@GetMapping("/{scopeId}/permissions")
	@Operation(summary = "可访问角色列表")
	@ResponseBody
	public Page<SysroleScope> getPermissions(@PathVariable("scopeId") String scopeId, @Nullable Pageable pageable);

	@PostMapping("/{scopeId}/permissions")
	@Operation(summary = "增加授权")
	@ResponseBody
	public void addPermissions(@PathVariable("scopeId") String scopeId, @RequestBody List<String> sysroleIds);

	@PutMapping("/{scopeId}/permissions/delete")
	@Operation(summary = "移除授权")
	@ResponseBody
	public void removePermissions(@PathVariable("scopeId") String scopeId, @RequestBody List<String> sysroleIds);
}
