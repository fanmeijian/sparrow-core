package cn.sparrow.permission.service;

import org.springframework.http.HttpMethod;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.model.permission.SparrowFunctionPK;
import cn.sparrow.model.permission.token.ApiPermissionToken;
import cn.sparrow.model.permission.token.DataPermissionToken;
import cn.sparrow.model.permission.token.FuncPermissionToken;
import cn.sparrow.model.permission.token.ModelPermissionToken;

public interface PermissionService<T> {
  public boolean hasPermission(EmployeeToken employeeToken, T permissionToken,
      PermissionEnum permission);

  public EmployeeToken buildEmployeeToken(String username);

  public ApiPermissionToken buildApiPermissionToken(String apiPath, HttpMethod method);

  public FuncPermissionToken buildFuncPermissionToken(SparrowFunctionPK functionPK);

  public ModelPermissionToken builModelPermissionToken(String modelName);

  public DataPermissionToken builDataPermissionToken(Object dataId, String modelName);
}
