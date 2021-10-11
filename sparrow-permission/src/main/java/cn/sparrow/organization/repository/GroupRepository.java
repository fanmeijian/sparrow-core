package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

}
