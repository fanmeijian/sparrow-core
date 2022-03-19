package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.token.PermissionToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "数据模型服务")
@RequestMapping("/models")
public interface ModelService{
	
	@Operation(summary = "浏览模型")
	@GetMapping("")
	@ResponseBody
	public Page<Model> models(@Nullable Pageable pageable,@Nullable Model model);

	@Operation(summary = "新增模型")
	@PostMapping("")
	@ResponseBody
	public Model create( @RequestBody Model model);

	@Operation(summary = "模型详情")
	@GetMapping("/{modelId}")
	@ResponseBody
	public Model getModel(@PathVariable("modelId") String modelId);

	@Operation(summary = "更新模型")
	@PatchMapping("/{modelId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Model.class)))
	public Model update(@PathVariable("modelId") String modelId, Map<String,Object> map);

	@Operation(summary = "删除模型")
	@PutMapping("/delete")
	@ResponseBody
	public void delete( @RequestBody List<String> ids);

	@Operation(summary = "设置模型权限")
	@PostMapping("/{modelId}/permissions")
	@ResponseBody
	public void addPermission(@PathVariable("modelId") String modelId ,@RequestBody PermissionToken permissionToken);

//	@Operation(summary = "设置模型权限")
//	@PostMapping("/permissions")
//	@ResponseBody
//	public void addPermissions( @RequestBody ModelPermission modelPermission);

	@Operation(summary = "删除模型权限")
	@PutMapping("/{modelId}/permissions/delete")
	@ResponseBody
	public void removePermission(@PathVariable("modelId") String modelId);
	
	public void init();
}
