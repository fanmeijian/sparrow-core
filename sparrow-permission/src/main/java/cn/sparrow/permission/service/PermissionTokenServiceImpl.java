package cn.sparrow.permission.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.permission.PermissionToken;
import cn.sparrow.model.permission.SparrowPermissionToken;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.repository.PermissionTokenRepository;

@Service
public class PermissionTokenServiceImpl implements PermissionTokenService {

  @Autowired PermissionTokenRepository permissionTokenRepository;
  @Autowired ModelRepository modelRepository;
  
  @Override
  public SparrowPermissionToken buildToken(@NotBlank String permissionId) {
    
    return permissionTokenRepository.findById(permissionId).get();
  }

  @Override
  public void update(String permissionTokenId, PermissionToken permissionToken) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removePermission(String permissionTokenId, PermissionToken permissionToken) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public SparrowPermissionToken create(PermissionToken permissionToken) {
    SparrowPermissionToken sparrowPermissionToken = new SparrowPermissionToken(permissionToken);
    return permissionTokenRepository.save(sparrowPermissionToken);
  }

  @Override
  public SparrowPermissionToken save(@NotNull PermissionToken permissionToken) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PermissionToken getModelPermissionToken(String modelName) {
    
    return modelRepository.findById(modelName).get().getModelPermissionToken();
  }


}
