package cn.sparrow.permission.mgt.api;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import cn.sparrow.permission.model.resource.SysroleApiPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "接口管理")
@RequestMapping("/apis")
public interface ApiService {

	@PostMapping("")
	@Operation(summary = "新增接口")
	@ResponseBody
	public int saveApis(@RequestBody List<SparrowApi> sparrowApis);

	@PatchMapping("/{apiId}")
	@Operation(summary = "更新接口")
	@ResponseBody
	public SparrowApi updateApi(@PathVariable("apiId") String apiId,@RequestBody SparrowApi sparrowApi);

	@GetMapping("/{apiId}")
	@Operation(summary = "获取接口详情")
	@ResponseBody
	public SparrowApi getUrl(@PathVariable("apiId") String id);

	@DeleteMapping("")
	@Operation(summary = "删除接口")
	@ResponseBody
	public void deleteByIds(@RequestBody List<String> ids);

	@GetMapping("")
	@Operation(summary = "浏览接口")
	@ResponseBody
	public Page<SparrowApi> all(@Nullable Pageable pageable);
	
	@GetMapping("/permissions")
	@Operation(summary = "可访问角色列表")
	@ResponseBody
	public Page<Sysrole> getPermissions(@Nullable @RequestParam("apiId") String apiId, @Nullable Pageable pageable);

	@PostMapping("/permissions")
	@Operation(summary = "增加授权")
	@ResponseBody
	public void addPermissions(@RequestBody List<SysroleApiPK> sysroleApiPKs);

	@DeleteMapping("/permissions")
	@Operation(summary = "移除授权")
	@ResponseBody
	public void delPermissions(@RequestBody List<SysroleApiPK> sysroleApiPKs);

	@PostMapping("/getPermissionByUrlId")
	public Page<SparrowApi> getPermissionByUrlId(@RequestBody final String[] ids);

	@PostMapping("/sparrowApis/batch")
	public List<SparrowApi> add(@NotNull @RequestBody final List<SparrowApi> urls);

	@DeleteMapping("/sparrowApis/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@GetMapping("/sparrowApis/permissions")
	public Page<?> getPermission(@Null @RequestParam("apiId") String urlId, Pageable pageable);

	@PostMapping("/sparrowApis/permissions")
	public void addPermission(@NotNull @RequestBody final List<SysroleApiPK> sysroleUrlPermissionPKs);

	@DeleteMapping("/sparrowApis/permissions")
	public void delPermission(@NotNull @RequestBody final List<SysroleApiPK> sysroleUrlPermissionPKs);
}
