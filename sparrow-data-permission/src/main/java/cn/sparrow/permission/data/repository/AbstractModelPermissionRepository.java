package cn.sparrow.permission.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractModelPermissionRepository<T, ID> extends JpaRepository<T, ID> {
	Long countByIdModelNameAndIdPermissionAndIdPermissionType(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType);
}
