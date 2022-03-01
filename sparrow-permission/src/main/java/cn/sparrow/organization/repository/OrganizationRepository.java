package cn.sparrow.organization.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.organization.Organization;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "organization-controller")
public interface OrganizationRepository extends JpaRepository<Organization, String> {

  @Transactional
  void deleteByIdIn(String[] ids);

  List<Organization> findByIsRoot(boolean b);
}
