package cn.sparrow.common.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sparrow.model.permission.DataPermission;

public class DataPermissionController {

	@GetMapping("/dataPermissions")
	public DataPermission dataPermissions(@NotNull @RequestParam("dataId") String dataId,@NotNull @RequestParam("modelName") String modelName){
		return null;
	}
	
	@PatchMapping("/dataPermissions/batch")
	public void update(@NotNull @RequestBody final List<DataPermission> dataPermissions) {

	}

	@DeleteMapping("/dataPermissions/batch")
	public void delete(@NotNull @RequestBody final List<DataPermission> dataPermissions) {

	}
	
}
