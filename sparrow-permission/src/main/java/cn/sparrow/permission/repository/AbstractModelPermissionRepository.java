package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;

@NoRepositoryBean
public interface AbstractModelPermissionRepository<T, ID> extends JpaRepository<T, ID> {
	Long countByIdModelNameAndIdPermissionAndIdPermissionType(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType);
}
