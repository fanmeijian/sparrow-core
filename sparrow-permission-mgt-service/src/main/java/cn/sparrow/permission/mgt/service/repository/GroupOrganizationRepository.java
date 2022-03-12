package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupOrganization;
import cn.sparrow.permission.model.group.GroupOrganizationPK;

public interface GroupOrganizationRepository extends JpaRepository<GroupOrganization, GroupOrganizationPK> {

	
	Page<GroupOrganization> findByIdGroupId(String groupId, Pageable pageable);

}
