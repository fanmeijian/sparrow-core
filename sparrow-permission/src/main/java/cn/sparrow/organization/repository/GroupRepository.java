package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.common.Group;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group-controller")
public interface GroupRepository extends JpaRepository<Group, String> {

}
