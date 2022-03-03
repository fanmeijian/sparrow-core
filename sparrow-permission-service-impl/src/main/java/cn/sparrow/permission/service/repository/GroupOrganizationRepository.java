package cn.sparrow.permission.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupOrganization;
import cn.sparrow.permission.model.group.GroupOrganizationPK;

public interface GroupOrganizationRepository extends JpaRepository<GroupOrganization, GroupOrganizationPK> {

	List<GroupOrganization> findByIdGroupId(@NotBlank String groupId);

}
