package cn.sparrow.permission.service;

import javax.validation.constraints.NotNull;
import cn.sparrow.model.permission.PermissionToken;
import cn.sparrow.model.permission.SparrowPermissionToken;

public interface PermissionTokenService {
//  public ApiPermissionToken buildApiPermissionToken(String apiPath, HttpMethod method);
//
//  public FuncPermissionToken buildFuncPermissionToken(SparrowFunctionPK functionPK);
//
//  public ModelPermissionToken builModelPermissionToken(String modelName);
//
//  public DataPermissionToken builDataPermissionToken(Object dataId, String modelName);
  
  public SparrowPermissionToken buildToken(String permissionId);
  public SparrowPermissionToken create(PermissionToken permissionToken);
  public void update(String permissionTokenId, PermissionToken permissionToken);
  public void removePermission(String permissionTokenId, PermissionToken permissionToken);
  public SparrowPermissionToken save(@NotNull PermissionToken permissionToken);
  public PermissionToken getModelPermissionToken(String modelName);
  
}
