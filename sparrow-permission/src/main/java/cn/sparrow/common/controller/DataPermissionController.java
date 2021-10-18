package cn.sparrow.common.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.model.permission.DataPermission;
import cn.sparrow.permission.service.DataPermissionService;

@RestController
public class DataPermissionController {

	@Autowired DataPermissionService dataPermissionService;
	
	
//	@GetMapping("/dataPermissions")
//	public DataPermission dataPermissions(@NotNull @RequestParam("dataId") String dataId,@NotNull @RequestParam("modelName") String modelName){
//		return null;
//	}
	
	@PatchMapping("/dataPermissions/batch")
	public void update(@NotNull @RequestBody final DataPermission permission) {
		dataPermissionService.addPermissions(permission);
	}

	@DeleteMapping("/dataPermissions/batch")
	public void delete(@NotNull @RequestBody final DataPermission permission) {
		dataPermissionService.delPermissions(permission);
	}
	
}
