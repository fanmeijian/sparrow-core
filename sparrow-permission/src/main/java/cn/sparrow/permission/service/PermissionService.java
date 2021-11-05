package cn.sparrow.permission.service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.EmployeeToken;

public interface PermissionService<T> {
  
  public boolean hasPermission(EmployeeToken employeeToken, T permissionToken, PermissionEnum permission);
  
  public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permission);
}
