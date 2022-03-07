package cn.sparrow.permission.mgt.api;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.SysroleApiPK;

@RequestMapping("/apis")
public interface ApiService {
	public int saveUrls(List<SparrowApi> sparrowUrls);

	public SparrowApi updateUrl(SparrowApi sparrowUrl);

	public SparrowApi getUrl(String id);

	public void deleteByIds(List<String> ids);

	public void delUrls(List<String> sparrowUrls);

	public Page<?> getPermissions(String urlId, Pageable pageable);

	public void addPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs);

	public void delPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs);

	@PostMapping("/sparrowApis/getPermissionByUrlId")
	public Page<SparrowApi> getPermissionByUrlId(@RequestBody final String[] ids);

	@PostMapping("/sparrowApis/batch")
	public List<SparrowApi> add(@NotNull @RequestBody final List<SparrowApi> urls);

	@DeleteMapping("/sparrowApis/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@GetMapping("/sparrowApis/permissions")
	public Page<?> getPermission(@Null @RequestParam("apiId") String urlId, Pageable pageable);

	@PostMapping("/sparrowApis/permissions")
	public void addPermission(@NotNull @RequestBody final List<SysroleApiPK> sysroleUrlPermissionPKs);

	@DeleteMapping("/sparrowApis/permissions")
	public void delPermission(@NotNull @RequestBody final List<SysroleApiPK> sysroleUrlPermissionPKs);
}
