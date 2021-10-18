package cn.sparrow.common.controller;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelPermission;

@RestController
public class ModelController {

	@GetMapping("/models")
	public Page<Model> getUrls(Pageable pageable) {
		return null;
	}
	
	@PostMapping("/models/batch")
	public void add(@NotNull @RequestBody final List<Model> models) {

	}

	@PatchMapping("/models/batch")
	public void update(@NotNull @RequestBody final List<Model> models) {

	}

	@DeleteMapping("/models/batch")
	public void delete(@NotNull @RequestBody final List<String> ids) {

	}

	@PostMapping("/models/permissions")
	public void addPermission(@NotNull @RequestBody final ModelPermission modelPermission) {

	}

	@DeleteMapping("/models/permissions")
	public void delPermission(@NotNull @RequestBody final ModelPermission modelPermission) {

	}

	
}
