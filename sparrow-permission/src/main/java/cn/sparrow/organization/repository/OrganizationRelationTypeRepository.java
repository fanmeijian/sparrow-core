package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.OrganizationRelationType;

@RepositoryRestResource(exported = false)
public interface OrganizationRelationTypeRepository extends JpaRepository<OrganizationRelationType, String> {

}
