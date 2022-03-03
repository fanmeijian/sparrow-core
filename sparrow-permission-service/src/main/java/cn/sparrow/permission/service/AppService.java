//package cn.sparrow.permission.service;
//
//import java.util.List;
//
//import javax.validation.constraints.NotNull;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.sparrow.permission.model.app.SparrowApp;
//import cn.sparrow.permission.repository.app.SparrowAppRepository;
//
//@RestController
//public class AppService {
//
//	@Autowired SparrowAppRepository appRepository;
//	
//	@GetMapping("/apps")
//	public Page<SparrowApp> apps(Pageable pageable){
//		return appRepository.findAll(pageable);
//	}
//	
//	@PostMapping("/apps/batch")
//	public void post(@NotNull @RequestBody final List<SparrowApp> apps) {
//		appRepository.saveAll(apps);
//	}
//	
//	@PatchMapping("/apps/batch")
//	public void patch(@NotNull @RequestBody List<SparrowApp> apps) {
//		appRepository.saveAll(apps);
//	}
//	
//	@DeleteMapping("/apps/batch")
//	public void delete(@NotNull @RequestBody final String[] ids) {
//		appRepository.deleteByIdIn(ids);
//	}
//	
//}
