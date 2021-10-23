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

import cn.sparrow.model.permission.Sysrole;
import cn.sparrow.model.permission.SysrolePermission;
import cn.sparrow.permission.repository.SysroleRepository;
import cn.sparrow.permission.service.SysroleService;

@RestController
public class SysroleController {

  @Autowired SysroleService sysroleService;
  @Autowired SysroleRepository sysroleRepository;
  
  @GetMapping("/sysroles")
  public Page<Sysrole> sysroles(@Nullable Pageable pageable){
	  return sysroleRepository.findAll(pageable);
  }
  
  @PostMapping("/sysroles/batch")
  public void add(@NotNull @RequestBody List<Sysrole> sysroles) {
	  sysroleRepository.saveAll(sysroles);
  }
  
  @PatchMapping("/sysroles/batch")
  public void udpate(@NotNull @RequestBody List<Sysrole> sysroles) {
	  sysroleRepository.saveAll(sysroles);
  }
  
  @DeleteMapping("/sysroles/batch")
  public void delete(@NotNull @RequestBody String[] ids) {
	  sysroleRepository.deleteByIdIn(ids);
  }
  
  @PostMapping("/sysroles/permissions")
  public void addPermissions(@NotNull @RequestBody SysrolePermission permission) {
	  sysroleService.addPermissions(permission);
  }
  
  @DeleteMapping("/sysroles/permissions")
  public void delPermissions(@NotNull @RequestBody SysrolePermission permission) {
	  sysroleService.delPermissions(permission);
  }
  
//  @PatchMapping("/sysroles/{sysroleId}/delMenus")
//  public void delMenus(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> menuIds) {
//      sysroleService.delMenus(sysroleId, menuIds);
//  }
//
//  @PatchMapping("/sysroles/{sysroleId}/addMenus")
//  public void addMenus(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> menuIds) {
//      sysroleService.addMenus(sysroleId, menuIds);
//  }
  
//  @PatchMapping("/sysroles/{sysroleId}/delUrlPermissions")
//  public void delUrlPermissions(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> urlIds) {
//      sysroleService.delUrlPermissions(sysroleId, urlIds);
//  }
//
//  @PatchMapping("/sysroles/{sysroleId}/addUrlPermissions")
//  public void addUrlPermissions(@PathVariable("sysroleId") String sysroleId, @RequestBody final List<String> urlIds) {
//      sysroleService.addUrlPermissions(sysroleId, urlIds);
//  }
//  
//  @PatchMapping("/sysroles/{sysroleId}/addModelPermission")
//  public void addModelPermission(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
//      sysroleService.addModelPermission(sysroleId, modelPermissionPKs);
//  }
//  
//  @PatchMapping("/sysroles/{sysroleId}/delModelPermission")
//  public void delModelPermission(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
//      sysroleService.delModelPermission(sysroleId, modelPermissionPKs);
//  }
//  
//  @PatchMapping("/sysroles/{sysroleId}/addDataPermission")
//  public void addDataPermissions(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
//      sysroleService.addDataPermissions(sysroleId, dataPermissionPKs);
//  }
//  
//  @PatchMapping("/sysroles/{sysroleId}/delDataPermission")
//  public void delDataPermissions(@NotEmpty @PathVariable("sysroleId") String sysroleId,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
//      sysroleService.delDataPermissions(sysroleId, dataPermissionPKs);
//  }
}
