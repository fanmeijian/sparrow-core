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

import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePermission;

@RestController
public class ModelAttributeController {

	@GetMapping("/modelAttributes")
	public Page<ModelAttribute> getUrls(Pageable pageable) {
		return null;
	}
	
	@PostMapping("/modelAttributes/batch")
	public void add(@NotNull @RequestBody final List<ModelAttribute> modelAttributes) {

	}

	@PatchMapping("/modelAttributes/batch")
	public void update(@NotNull @RequestBody final List<ModelAttribute> modelAttributes) {

	}

	@DeleteMapping("/modelAttributes/batch")
	public void delete(@NotNull @RequestBody final List<String> ids) {

	}

	@PostMapping("/modelAttributes/permissions")
	public void addPermission(@NotNull @RequestBody final ModelAttributePermission modelAttributePermission) {

	}

	@DeleteMapping("/modelAttributes/permissions")
	public void delPermission(@NotNull @RequestBody final ModelAttributePermission modelAttributePermission) {

	}

	
}
