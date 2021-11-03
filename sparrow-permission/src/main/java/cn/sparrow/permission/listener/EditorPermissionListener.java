package cn.sparrow.permission.listener;

import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.permission.service.IPermission;

@Component
public final class EditorPermissionListener {

  private static IPermission<AbstractModelPermissionPK> modelPermissionService;

  @Autowired
  public void setModelIPermission(IPermission<AbstractModelPermissionPK> modelPermissionService) {
    EditorPermissionListener.modelPermissionService = modelPermissionService;
  }

  @PreUpdate
  private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
    String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
        : SecurityContextHolder.getContext().getAuthentication().getName();
    // 检查是否有新建权限
    // 用户是否在拒绝权限列表
    if (modelPermissionService
        .hasPermission(new AbstractModelPermissionPK(abstractEntity.getClass().getName(),
            PermissionEnum.EDITOR, PermissionTypeEnum.DENY), username)) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_U_DN-40",
              "模型拒绝编辑权限" + abstractEntity.getClass().getName() + username));
    }

    // 1.先检查模型的作者权限
    if (!modelPermissionService
        .hasPermission(new AbstractModelPermissionPK(abstractEntity.getClass().getName(),
            PermissionEnum.EDITOR, PermissionTypeEnum.ALLOW), username)) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_U-40",
              "无模型编辑权限" + abstractEntity.getClass().getName() + username));
    }
  }
}
