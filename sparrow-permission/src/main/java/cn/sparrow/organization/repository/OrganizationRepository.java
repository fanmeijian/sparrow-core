package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
