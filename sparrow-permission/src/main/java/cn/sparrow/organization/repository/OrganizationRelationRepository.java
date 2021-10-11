package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;

public interface OrganizationRelationRepository extends JpaRepository<OrganizationRelation, OrganizationRelationPK> {

}
