package cn.sparrow.permission.service;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sparrow.permission.model.ModelAttribute;
import cn.sparrow.permission.model.ModelAttributePK;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/modelAttributes")
public interface ModelAttributeService {
	
	@Operation(summary = "获取模型属性列表")
	@GetMapping("/modelAttributes")
	public Page<?> pages(@Nullable Pageable pageable);

	@Operation(summary = "批量新增模型属性")
	@PostMapping("/modelAttributes/batch")
	public void add(@NotNull @RequestBody final List<ModelAttribute> modelAttributes);

	@Operation(summary = "批量更新模型属性")
	@PatchMapping("/modelAttributes/batch")
	public void update(@NotNull @RequestBody final List<ModelAttribute> modelAttributes);

	@Operation(summary = "批量删除模型属性")
	@DeleteMapping("/modelAttributes/batch")
	public void delete(@NotNull @RequestBody final List<ModelAttributePK> ids);
}
