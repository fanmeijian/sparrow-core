package cn.sparrow.permission.data.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractModelAttributePermissionRepository<T, ID> extends AbstractModelPermissionRepository<T, ID> {
	Long countByIdModelNameAndIdAttributeNameAndIdPermission(String modelName, String attributeName, PermissionEnum permission);
}
