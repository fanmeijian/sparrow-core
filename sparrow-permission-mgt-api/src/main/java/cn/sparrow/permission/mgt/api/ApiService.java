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

import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.Sysrole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "接口服务")
@RequestMapping("/apis")
public interface ApiService{

	@PostMapping("")
	@Operation(summary = "新增接口")
	@ResponseBody
	public SparrowApi create(@RequestBody SparrowApi sparrowApi);

	@PatchMapping("/{apiId}")
	@Operation(summary = "更新接口")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = SparrowApi.class)))
	@ResponseBody
	public SparrowApi update(@PathVariable("apiId") String apiId, @RequestBody Map<String, Object> map);

	@GetMapping("/{apiId}")
	@Operation(summary = "获取接口详情")
	@ResponseBody
	public SparrowApi get(@PathVariable("apiId") String id);

	@PutMapping("/delete")
	@Operation(summary = "删除接口")
	@ResponseBody
	public void delete(@RequestBody List<String> ids);

	@GetMapping("")
	@Operation(summary = "浏览接口")
	@ResponseBody
	public Page<SparrowApi> all(@Nullable Pageable pageable, @Nullable SparrowApi sparrowApi);

	@GetMapping("/{apiId}/permissions")
	@Operation(summary = "可访问角色列表")
	@ResponseBody
	public Page<Sysrole> getPermissions(@PathVariable("apiId") String apiId, @Nullable Pageable pageable);

	@PostMapping("/{apiId}/permissions")
	@Operation(summary = "增加授权")
	@ResponseBody
	public void addPermissions(@PathVariable("apiId") String apiId, @RequestBody List<String> sysroleIds);

	@PutMapping("/{apiId}/permissions/delete")
	@Operation(summary = "移除授权")
	@ResponseBody
	public void removePermissions(@PathVariable("apiId") String apiId, @RequestBody List<String> sysroleIds);
}
