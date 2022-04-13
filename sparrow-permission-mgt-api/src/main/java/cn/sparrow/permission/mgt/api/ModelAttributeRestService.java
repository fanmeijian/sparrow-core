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

import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "datamodel", description = "数据模型服务")
@RequestMapping("/models")
public interface ModelAttributeRestService {

	@Operation(summary = "属性列表")
	@GetMapping("/{modelId}/attributes")
	@ResponseBody
	public Page<ModelAttribute> allAttributes(@PathVariable("modelId") String modelId, @Nullable Pageable pageable,
			@Nullable ModelAttribute modelAttribute);

	@Operation(summary = "新增属性")
	@PostMapping("/attributes")
	@ResponseBody
	public ModelAttribute createAttribute(@RequestBody ModelAttribute modelAttribute);

	@Operation(summary = "属性详情")
	@GetMapping("/{modelId}/attributes/{attributeId}")
	@ResponseBody
	public ModelAttribute getAttribute(@PathVariable("modelId") String modelId,
			@PathVariable("attributeId") String attributeId);

	@Operation(summary = "更新属性")
	@PatchMapping("/{modelId}/attributes/{attributeId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ModelAttribute.class)))
	public ModelAttribute updateAttribute(@PathVariable("modelId") String modelId,
			@PathVariable("attributeId") String attributeId, @RequestBody Map<String, Object> map);

	@Operation(summary = "设置属性权限")
	@PostMapping("/{modelId}/attributes/{attributeId}/permissions")
	@ResponseBody
	public SparrowPermissionToken addAttributePermission(@PathVariable("modelId") String modelId,
			@PathVariable("attributeId") String attributeId, @RequestBody PermissionToken permissionToken);

	@Operation(summary = "取消属性权限")
	@DeleteMapping("/{modelId}/attributes/{attributeId}/permissions")
	@ResponseBody
	public void removeAttributePermission(@PathVariable("modelId") String modelId,
			@PathVariable("attributeId") String attributeId);

	@Operation(summary = "删除属性")
	@PutMapping("/{modelId}/attributes/delete")
	@ResponseBody
	public void deleteAttribute(@PathVariable("modelId") String modelId, @RequestBody List<String> attributeIds);
}
