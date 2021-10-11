package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.OrganizationType;

public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, String> {

}
