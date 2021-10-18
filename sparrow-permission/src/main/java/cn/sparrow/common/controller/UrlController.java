package cn.sparrow.common.controller;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.permission.service.UrlService;

@RestController
public class UrlController {

	@Autowired
	UrlService urlService;

	@GetMapping("/sparrowUrls")
	public Page<SparrowUrl> getUrls(Pageable pageable) {
		return null;
	}

	@PostMapping("/sparrowUrls/batch")
	public void add(@NotNull @RequestBody final List<SparrowUrl> urls) {

	}

	@PatchMapping("/sparrowUrls/batch")
	public void update(@NotNull @RequestBody final List<SparrowUrl> urls) {

	}

	@DeleteMapping("/sparrowUrls/batch")
	public void delete(@NotNull @RequestBody final List<String> urls) {

	}

	@PostMapping("/sparrowUrls/permissions")
	public void addPermission(@NotNull @RequestBody final List<SysroleUrlPermission> sysroleUrlPermissions) {

	}

	@DeleteMapping("/sparrowUrls/permissions")
	public void delPermission(@NotNull @RequestBody final List<SysroleUrlPermission> sysroleUrlPermissions) {

	}
}
