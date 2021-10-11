package cn.sparrow.permission.data.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDataFieldPermissionRepository<T, ID> extends AbstractModelAttributePermissionRepository<T, ID> {
	Long countByIdModelNameAndIdAttributeNameAndIdPermissionAndIdDataId(String modelName, String attributeName, PermissionEnum permission, String dataId);
}
