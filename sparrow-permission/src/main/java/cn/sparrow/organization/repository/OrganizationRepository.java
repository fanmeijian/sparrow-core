package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.Organization;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "organization-controller")
public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
