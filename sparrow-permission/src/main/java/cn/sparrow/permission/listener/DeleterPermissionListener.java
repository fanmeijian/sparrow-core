package cn.sparrow.permission.listener;

import org.springframework.stereotype.Component;

@Component
public final class DeleterPermissionListener {

//  private static IPermission<ModelPermissionPK> modelPermissionService;
//
//  @Autowired
//  public void setModelIPermission(IPermission<ModelPermissionPK> modelPermissionService) {
//    DeleterPermissionListener.modelPermissionService = modelPermissionService;
//  }
//
//  @PreRemove
//  private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
//    String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
//        : SecurityContextHolder.getContext().getAuthentication().getName();
//    // 检查是否有新建权限
//    // 用户是否在拒绝权限列表
//    if (modelPermissionService
//        .hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
//            PermissionEnum.DELETER, PermissionTypeEnum.DENY), username)) {
//      throw new RepositoryConstraintViolationException(
//          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_D_DN-40",
//              "模型拒绝删除权限" + abstractEntity.getClass().getName() + username));
//    }
//
//    // 1.先检查模型的作者权限
//    if (!modelPermissionService
//        .hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
//            PermissionEnum.DELETER, PermissionTypeEnum.ALLOW), username)) {
//      throw new RepositoryConstraintViolationException(
//          RepositoryErrorFactory.getErros(abstractEntity, "SPR_MD_D-40",
//              "无模型删除权限" + abstractEntity.getClass().getName() + username));
//    }
//  }
}
