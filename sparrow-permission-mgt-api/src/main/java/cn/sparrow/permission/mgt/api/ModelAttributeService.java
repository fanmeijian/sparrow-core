package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "模型属性服务")
@RequestMapping("/modelAttributes")
public interface ModelAttributeService {
	
	@Operation(summary = "模型属性列表")
	@GetMapping("")
	@ResponseBody
	public Page<ModelAttribute> all(@Nullable Pageable pageable, @Nullable ModelAttribute modelAttribute);

	@Operation(summary = "新增模型属性")
	@PostMapping("")
	@ResponseBody
	public ModelAttribute create( @RequestBody ModelAttribute modelAttribute);

	@Operation(summary = "更新模型属性")
	@PatchMapping("")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ModelAttribute.class)))
	public ModelAttribute update(@RequestBody Map<String, Object> map);

	@Operation(summary = "删除模型属性")
	@DeleteMapping("")
	public void delete( @RequestBody List<ModelAttributePK> ids);
}
