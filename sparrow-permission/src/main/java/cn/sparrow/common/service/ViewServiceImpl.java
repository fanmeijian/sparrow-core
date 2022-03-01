package cn.sparrow.common.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.organization.service.EmployeeTokenService;
import cn.sparrow.permission.service.PermissionService;

@Service
public class ViewServiceImpl implements ViewService {

  @Autowired
  PermissionService permissionService;
  @Autowired
  EmployeeTokenService employeeTokenService;

  @Override
  public void filterNoReadObject(Iterable<AbstractSparrowEntity> iterable) {
    Iterator<?> iterator = iterable.iterator();
//    while (iterator.hasNext()) {
//      AbstractSparrowEntity sparrowEntity = (AbstractSparrowEntity) iterator.next();
//      if (sparrowEntity.getSparrowDataPermissionToken()!=null && !permissionService.hasPermission(
//          employeeTokenService
//              .getEmployeeToken(SecurityContextHolder.getContext().getAuthentication().getName()),
//          sparrowEntity.getSparrowDataPermissionToken().getPermissionToken(), PermissionEnum.READER)) {
//        iterator.remove();
//      }
//    }
  }
}
