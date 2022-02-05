package cn.sparrow.common.controller;

import java.util.List;
import javax.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sun.istack.NotNull;
import cn.sparrow.model.permission.SparrowApi;
import cn.sparrow.permission.repository.ApiRepository;
import cn.sparrow.permission.service.ApiService;

@RestController
public class ApiController {

	@Autowired ApiService apiService;
	@Autowired ApiRepository apiRepository;
//	@Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
	
//	@GetMapping("/sparrowApis")
//	public Page<SparrowApi> getUrls(@Nullable Pageable pageable) {
//		return apiRepository.findAll(pageable);
//	}
	
//	@PostMapping("/sparrowApis/getPermissionById")
//	public Page<SysroleUrlPermission> getPermissionById(@RequestBody final String[] ids){
//	  return sysroleUrlPermissionRepository.findByIdUrlIdIn(ids, Pageable.unpaged());
//	}
	
	@PostMapping("/sparrowApis/getPermissionByUrlId")
    public Page<SparrowApi> getPermissionByUrlId(@RequestBody final String[] ids){
      return apiRepository.findByIdIn(ids, Pageable.unpaged());
    }

	@PostMapping("/sparrowApis/batch")
	public List<SparrowApi> add(@NotNull @RequestBody final List<SparrowApi> urls) {
		return apiRepository.saveAll(urls);
	}

	@PatchMapping("/sparrowApis/batch")
	public List<SparrowApi> update(@NotNull @RequestBody final List<SparrowApi> urls) {
		return apiRepository.saveAll(urls);
	}

	@DeleteMapping("/sparrowApis/batch")
	public void delete(@NotNull @RequestBody final String[] ids) {
		apiRepository.deleteByIdIn(ids);
	}

//	@GetMapping("/sparrowApis/permissions")
//	public Page<?> getPermission(@Nullable @RequestParam("urlId") String urlId, Pageable pageable) {
//		return urlService.getPermissions(urlId, pageable);
//	}
//	
//	@PostMapping("/sparrowApis/permissions")
//	public void addPermission(@NotNull @RequestBody final List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		urlService.addPermissions(sysroleUrlPermissionPKs);
//	}
//
//	@DeleteMapping("/sparrowApis/permissions")
//	public void delPermission(@NotNull @RequestBody final List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		urlService.delPermissions(sysroleUrlPermissionPKs);
//	}
}
