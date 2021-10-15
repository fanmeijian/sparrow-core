package cn.sparrow.common.controller;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sun.istack.NotNull;

import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.permission.service.SysroleService;

@RestController
public class SysroleController {

  @Autowired SysroleService sysroleService;
  
  @PatchMapping("/sysroles/{sysroleId}/delMenus")
  public void delMenus(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> menuIds) {
      sysroleService.delMenus(sysroleId, menuIds);
  }

  @PatchMapping("/sysroles/{sysroleId}/addMenus")
  public void addMenus(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> menuIds) {
      sysroleService.addMenus(sysroleId, menuIds);
  }
  
  @PatchMapping("/sysroles/{sysroleId}/delUrlPermissions")
  public void delUrlPermissions(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> urlIds) {
      sysroleService.delUrlPermissions(sysroleId, urlIds);
  }

  @PatchMapping("/sysroles/{sysroleId}/addUrlPermissions")
  public void addUrlPermissions(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> urlIds) {
      sysroleService.addUrlPermissions(sysroleId, urlIds);
  }
  
  @PatchMapping("/sysroles/{sysroleId}/addModelPermission")
  public void addModelPermission(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
      sysroleService.addModelPermission(sysroleId, modelPermissionPKs);
  }
  
  @PatchMapping("/sysroles/{sysroleId}/delModelPermission")
  public void delModelPermission(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
      sysroleService.delModelPermission(sysroleId, modelPermissionPKs);
  }
  
  @PatchMapping("/sysroles/{sysroleId}/addDataPermission")
  public void addDataPermissions(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
      sysroleService.addDataPermissions(sysroleId, dataPermissionPKs);
  }
  
  @PatchMapping("/sysroles/{sysroleId}/delDataPermission")
  public void delDataPermissions(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
      sysroleService.delDataPermissions(sysroleId, dataPermissionPKs);
  }
}
