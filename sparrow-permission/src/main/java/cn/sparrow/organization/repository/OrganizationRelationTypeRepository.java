package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.OrganizationRelationType;

public interface OrganizationRelationTypeRepository extends JpaRepository<OrganizationRelationType, String> {

}
