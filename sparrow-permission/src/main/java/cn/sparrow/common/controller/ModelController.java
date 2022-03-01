package cn.sparrow.common.controller;

import java.util.List;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelPermission;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.service.ModelService;

@RestController
public class ModelController {

	@Autowired ModelService modelService;
	@Autowired ModelRepository modelRepository;
//	@Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
	
	@GetMapping("/models")
	public Page<Model> models(@Nullable Pageable pageable) {
		return modelRepository.findAll(pageable);
	}
	
//	@GetMapping("/models/sysroleModelPermissions")
//    public Page<SysroleModelPermission> sysroleModelPermissions(@Nullable Pageable pageable) {
//        return sysroleModelPermissionRepository.findAll(pageable);
//    }
	
	@PostMapping("/models")
	public void save(@NotNull @RequestBody final Model model) {
		modelRepository.save(model);
	}

	
	@PostMapping("/models/getModelsInId")
	public Page<Model> getModelsInId(@NotNull @RequestBody String[] ids,@Nullable Pageable pageable) {
		return modelRepository.findByNameIn(ids,pageable);
	}
	
	@PostMapping("/models/batch")
	public void add(@NotNull @RequestBody final List<Model> models) {
		modelRepository.saveAll(models);
	}

	@PatchMapping("/models/batch")
	public void update(@NotNull @RequestBody final List<Model> models) {
		modelRepository.saveAll(models);
	}

	@DeleteMapping("/models/batch")
	public void delete(@NotNull @RequestBody final String[] ids) {
		modelRepository.deleteByNameIn(ids);
	}

	@PostMapping("/models/permissions")
	public void addPermission(@NotNull @RequestBody final ModelPermission modelPermission) {
		modelService.addPermissions(modelPermission);
	}

	@DeleteMapping("/models/permissions")
	public void delPermission(@NotNull @RequestBody final ModelPermission modelPermission) {
		modelService.delPermissions(modelPermission);
	}

	
}
