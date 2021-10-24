package cn.sparrow.organization.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.RoleRelation;
import cn.sparrow.model.organization.RoleRelationPK;

@RepositoryRestResource(exported = false)
public interface RoleRelationRepository extends JpaRepository<RoleRelation, RoleRelationPK> {

	List<RoleRelation> findByIdParentId(String parentId);

	@Transactional
	void deleteByIdRoleIdInOrIdParentIdIn(String[] ids, String[] ids2);

}
