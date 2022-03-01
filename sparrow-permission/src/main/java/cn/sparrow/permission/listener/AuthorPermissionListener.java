package cn.sparrow.permission.listener;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.AbstractSparrowEntity;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;
import cn.sparrow.permission.service.organization.EmployeeTokenService;

@Component
public final class AuthorPermissionListener {

  private static PermissionService permissionService;
  private static PermissionTokenService permissionTokenService;
  private static EmployeeTokenService employeeTokenService;

  @Autowired
  public void setPermissionService(PermissionService permissionService) {
    AuthorPermissionListener.permissionService = permissionService;
  }

  @Autowired
  public void setPermissionTokenService(PermissionTokenService permissionTokenService) {
    AuthorPermissionListener.permissionTokenService = permissionTokenService;
  }

  @Autowired
  public void setEmployeeTokenService(EmployeeTokenService employeeTokenService) {
    AuthorPermissionListener.employeeTokenService = employeeTokenService;
  }

  // 新建和编辑单据权限检查
  @PrePersist
  private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
    String username = abstractEntity.getCreatedBy();
    // 检查是否有新建权限
    // 用户是否在拒绝权限列表

    if (!permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
        permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
        PermissionEnum.AUTHOR)) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_C_DN-40",
              "模型拒绝新建权限" + abstractEntity.getClass().getName() + username));
    }
  }
}
