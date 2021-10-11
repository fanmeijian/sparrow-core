package cn.sparrow.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.sysrole.Sysrole;

public interface SysroleRepository extends JpaRepository<Sysrole, Long>{

	Page<Sysrole> findByNameContaining(String name,Pageable p);
}
