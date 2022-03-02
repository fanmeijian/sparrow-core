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

import cn.sparrow.permission.model.Model;
import cn.sparrow.permission.model.ModelPermission;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/models")
public interface ModelService {
	
	@Operation(summary = "获取模型列表")
	@GetMapping("/models")
	public Page<Model> models(@Nullable Pageable pageable);

	@Operation(summary = "更新模型")
	@PostMapping("/models")
	public void save(@NotNull @RequestBody final Model model);

	@Operation(summary = "获取指定模型")
	@PostMapping("/models/getModelsInId")
	public Page<Model> getModelsInId(@NotNull @RequestBody String[] ids, @Nullable Pageable pageable);

	@Operation(summary = "批量新增模型")
	@PostMapping("/models/batch")
	public void add(@NotNull @RequestBody final List<Model> models);

	@Operation(summary = "批量更新模型")
	@PatchMapping("/models/batch")
	public void update(@NotNull @RequestBody final List<Model> models);

	@Operation(summary = "批量删除模型")
	@DeleteMapping("/models/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@Operation(summary = "设置模型权限")
	@PostMapping("/models/permissions")
	public void addPermission(@NotNull @RequestBody final ModelPermission modelPermission);

	@Operation(summary = "删除模型权限")
	@DeleteMapping("/models/permissions")
	public void delPermission(@NotNull @RequestBody final ModelPermission modelPermission);
	
	public void init();
}
