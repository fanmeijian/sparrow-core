package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.permission.SysroleFile;
import cn.sparrow.model.permission.SysroleFilePK;

@RepositoryRestResource(exported = false)
public interface SysroleFileRepository extends JpaRepository<SysroleFile, SysroleFilePK> {

	List<SysroleFile> findByIdFileId(String fileId);

	
}
