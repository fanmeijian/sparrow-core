package cn.sparrow.permission.repository;

import org.springframework.data.repository.NoRepositoryBean;
import cn.sparrow.model.permission.PermissionEnum;

@NoRepositoryBean
public interface AbstractModelAttributePermissionRepository<T, ID> extends AbstractModelPermissionRepository<T, ID> {
	Long countByIdModelNameAndIdAttributeNameAndIdPermission(String modelName, String attributeName, PermissionEnum permission);
}
