package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import cn.sparrow.model.sysrole.Sysrole;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "sysrole-controller")
public interface SysroleRepository extends JpaRepository<Sysrole, String>{

  @RestResource(exported = true)
	Page<Sysrole> findByNameContaining(String name,Pageable p);
	
	List<Sysrole> findByName(String name);
}
