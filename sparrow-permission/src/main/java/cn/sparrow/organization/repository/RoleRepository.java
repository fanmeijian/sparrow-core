package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
