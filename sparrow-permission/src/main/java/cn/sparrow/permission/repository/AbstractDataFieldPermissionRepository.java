package cn.sparrow.permission.repository;

import org.springframework.data.repository.NoRepositoryBean;
import cn.sparrow.model.permission.PermissionEnum;

@NoRepositoryBean
public interface AbstractDataFieldPermissionRepository<T, ID> extends AbstractModelAttributePermissionRepository<T, ID> {
	Long countByIdModelNameAndIdAttributeNameAndIdPermissionAndIdDataId(String modelName, String attributeName, PermissionEnum permission, String dataId);
}
