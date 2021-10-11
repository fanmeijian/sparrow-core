package cn.sparrow.permission.data.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDataPermissionRepository<T, ID> extends AbstractModelPermissionRepository<T, ID> {
	Long countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(String modelName, PermissionEnum permission,PermissionTypeEnum permissionType, String dataId);
}
