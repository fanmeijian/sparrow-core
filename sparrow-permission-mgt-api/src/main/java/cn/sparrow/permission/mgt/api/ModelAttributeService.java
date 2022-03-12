package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;
import cn.sparrow.permission.model.token.PermissionToken;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

//@Tag(name = "模型属性服务")
//@RequestMapping("/modelAttributes")
public interface ModelAttributeService extends ModelAttributeRestService{

//	@Operation(summary = "模型属性列表")
//	@GetMapping("")
//	@ResponseBody
	public Page<ModelAttribute> all(@Nullable Pageable pageable, @Nullable ModelAttribute modelAttribute);

//	@Operation(summary = "新增模型属性")
//	@PostMapping("")
//	@ResponseBody
	public ModelAttribute create(@RequestBody ModelAttribute modelAttribute);

//	@Operation(summary = "模型属性详情")
//	@GetMapping("/{attributeId}")
//	@ResponseBody
	public ModelAttribute get(
			@Parameter(content = @Content(schema = @Schema(implementation = String.class)), example = "modelName_attributeName") @PathVariable("attributeId") ModelAttributePK attributePK);

//	@Operation(summary = "更新模型属性")
//	@PatchMapping("/{attributeId}")
//	@ResponseBody
//	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ModelAttribute.class)))
	public ModelAttribute update(
			@Parameter(content = @Content(schema = @Schema(implementation = String.class)), example = "modelName_attributeName") @PathVariable("attributeId") ModelAttributePK attributePK,
			@RequestBody Map<String, Object> map);

//	@Operation(summary = "设置属性权限")
//	@PostMapping("/{attributeId}/permissions")
//	@ResponseBody
	public void addPermission(
			@Parameter(content = @Content(schema = @Schema(implementation = String.class)), example = "modelName_attributeName") @PathVariable("attributeId") ModelAttributePK attributePK,
			@RequestBody PermissionToken permissionToken);

//	@Operation(summary = "移除属性权限")
//	@DeleteMapping("/{attributeId}/permissions")
//	@ResponseBody
	public void removePermission(
			@Parameter(content = @Content(schema = @Schema(implementation = String.class)), example = "modelName_attributeName") @PathVariable("attributeId") ModelAttributePK attributePK);

//	@Operation(summary = "删除模型属性")
//	@DeleteMapping("")
	public void delete(@RequestBody List<ModelAttributePK> ids);
}
