package cn.sparrow.group.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupOrganization;
import cn.sparrow.model.group.GroupOrganizationPK;

@RepositoryRestResource(exported = false)
public interface GroupOrganizationRepository extends JpaRepository<GroupOrganization, GroupOrganizationPK> {

	List<GroupOrganization> findByIdGroupId(@NotBlank String groupId);

}
