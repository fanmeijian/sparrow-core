//package cn.sparrow.common.controller;
//
//import javax.validation.constraints.NotNull;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.sparrow.model.permission.DataFieldPermission;
//
//@RestController
//public class DataFieldController {
//
//	@GetMapping("/dataFieldPermissions")
//	public DataFieldPermission dataFieldPermission(@NotNull @RequestParam("dataId") String dataId,@NotNull @RequestParam("modelName") String modelName,@NotNull @RequestParam("modelAttributeName") String modelAttributeName){
//		return null;
//	}
//	
//	@PatchMapping("/dataFieldPermissions/batch")
//	public void update(@NotNull @RequestBody final DataFieldPermission dataFieldPermission) {
//
//	}
//
//	@DeleteMapping("/dataFieldPermissions/batch")
//	public void delete(@NotNull @RequestBody final DataFieldPermission dataFieldPermission) {
//
//	}
//}
