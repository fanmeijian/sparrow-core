package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.Group;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group-controller")
@RepositoryRestResource(exported = false)
public interface GroupRepository extends JpaRepository<Group, String> {

}
