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
import cn.sparrow.common.service.UserService;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.AbstractModelPermissionPK;

@RestController
public class UserController {

  @Autowired UserService userService;
  
  @PatchMapping("/users/{username}/delMenus")
  public void removeMenus(@PathVariable("username") String username, @NotNull @RequestBody  final List<String> menuIds) {
      userService.delMenus(username, menuIds);
  }

  @PatchMapping("/users/{username}/addMenus")
  public void addMenus(@PathVariable("username") String username, @NotNull @RequestBody  final List<String> menuIds) {
      userService.addMenus(username, menuIds);
  }
  
  @PatchMapping("/users/{username}/addModelPermissions")
  public void addModelPermissions(@NotEmpty @PathVariable("username") String username,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
      userService.addModelPermissions(username, modelPermissionPKs);
  }
  
  @PatchMapping("/users/{username}/delModelPermissions")
  public void delModelPermissions(@NotEmpty @PathVariable("username") String username,@NotNull @RequestBody final Set<AbstractModelPermissionPK> modelPermissionPKs) {
      userService.delModelPermissions(username, modelPermissionPKs);
  }
  
  @PatchMapping("/users/{username}/addDataPermissions")
  public void addDataPermissions(@NotEmpty @PathVariable("username") String username,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
      userService.addDataPermissions(username, dataPermissionPKs);
  }
  
  @PatchMapping("/users/{username}/delDataPermissions")
  public void delDataPermissions(@NotEmpty @PathVariable("username") String username,@NotNull @RequestBody final Set<AbstractDataPermissionPK> dataPermissionPKs) {
      userService.delDataPermissions(username, dataPermissionPKs);
  }
}
