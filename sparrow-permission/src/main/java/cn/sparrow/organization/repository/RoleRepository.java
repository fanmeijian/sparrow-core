package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Role;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "role-controller")
public interface RoleRepository extends JpaRepository<Role, String> {

}
