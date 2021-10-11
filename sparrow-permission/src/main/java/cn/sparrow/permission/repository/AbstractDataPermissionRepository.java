package cn.sparrow.permission.repository;

import org.springframework.data.repository.NoRepositoryBean;
import cn.sparrow.model.permission.PermissionEnum;
import cn.sparrow.model.permission.PermissionTypeEnum;

@NoRepositoryBean
public interface AbstractDataPermissionRepository<T, ID> extends AbstractModelPermissionRepository<T, ID> {
	Long countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(String modelName, PermissionEnum permission,PermissionTypeEnum permissionType, String dataId);
}
