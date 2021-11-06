package cn.sparrow.permission.listener;

import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.ModelPermissionPK;
import cn.sparrow.model.permission.PermissionToken;
import cn.sparrow.organization.service.EmployeeTokenService;
import cn.sparrow.permission.service.IPermission;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;

@Component
public final class AuthorPermissionListener {

  private static IPermission<ModelPermissionPK> modelPermissionService;

  private static PermissionService<PermissionToken> permissionService;
  private static PermissionTokenService permissionTokenService;
  private static EmployeeTokenService employeeTokenService;

  @Autowired
  public void setModelIPermission(IPermission<ModelPermissionPK> modelPermissionService) {
    AuthorPermissionListener.modelPermissionService = modelPermissionService;
  }

  @Autowired
  public void setPermissionService(PermissionService<PermissionToken> permissionService) {
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
    String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
        : SecurityContextHolder.getContext().getAuthentication().getName();
    // 检查是否有新建权限
    // 用户是否在拒绝权限列表
    // if (modelPermissionService
    // .hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
    // PermissionEnum.AUTHOR, PermissionTypeEnum.DENY), username)) {
    // throw new RepositoryConstraintViolationException(
    // RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_C_DN-40",
    // "模型拒绝新建权限" + abstractEntity.getClass().getName() + username));
    // }

    if (permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
        permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
        PermissionEnum.AUTHOR)) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_C_DN-40",
              "模型拒绝新建权限" + abstractEntity.getClass().getName() + username));
    }

    // 1.先检查模型的作者权限
    if (!modelPermissionService
        .hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
            PermissionEnum.AUTHOR, PermissionTypeEnum.ALLOW), username)) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_C-40",
              "无模型新建权限" + abstractEntity.getClass().getName() + username));
    }
  }
}
