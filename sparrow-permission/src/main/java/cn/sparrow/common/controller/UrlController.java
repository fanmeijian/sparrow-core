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
import cn.sparrow.permission.repository.UrlRepository;
import cn.sparrow.permission.service.UrlService;

@RestController
public class UrlController {

	@Autowired UrlService urlService;
	@Autowired UrlRepository urlRepository;
//	@Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
	
	@GetMapping("/sparrowUrls")
	public Page<SparrowApi> getUrls(@Nullable Pageable pageable) {
		return urlRepository.findAll(pageable);
	}
	
//	@PostMapping("/sparrowUrls/getPermissionById")
//	public Page<SysroleUrlPermission> getPermissionById(@RequestBody final String[] ids){
//	  return sysroleUrlPermissionRepository.findByIdUrlIdIn(ids, Pageable.unpaged());
//	}
	
	@PostMapping("/sparrowUrls/getPermissionByUrlId")
    public Page<SparrowApi> getPermissionByUrlId(@RequestBody final String[] ids){
      return urlRepository.findByIdIn(ids, Pageable.unpaged());
    }

	@PostMapping("/sparrowUrls/batch")
	public List<SparrowApi> add(@NotNull @RequestBody final List<SparrowApi> urls) {
		return urlRepository.saveAll(urls);
	}

	@PatchMapping("/sparrowUrls/batch")
	public List<SparrowApi> update(@NotNull @RequestBody final List<SparrowApi> urls) {
		return urlRepository.saveAll(urls);
	}

	@DeleteMapping("/sparrowUrls/batch")
	public void delete(@NotNull @RequestBody final String[] ids) {
		urlRepository.deleteByIdIn(ids);
	}

//	@GetMapping("/sparrowUrls/permissions")
//	public Page<?> getPermission(@Nullable @RequestParam("urlId") String urlId, Pageable pageable) {
//		return urlService.getPermissions(urlId, pageable);
//	}
//	
//	@PostMapping("/sparrowUrls/permissions")
//	public void addPermission(@NotNull @RequestBody final List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		urlService.addPermissions(sysroleUrlPermissionPKs);
//	}
//
//	@DeleteMapping("/sparrowUrls/permissions")
//	public void delPermission(@NotNull @RequestBody final List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		urlService.delPermissions(sysroleUrlPermissionPKs);
//	}
}
