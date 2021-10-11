package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.file.SysroleFile;
import cn.sparrow.model.file.SysroleFilePK;

public interface SysroleFileRepository extends JpaRepository<SysroleFile, SysroleFilePK> {

	List<SysroleFile> findByIdFileId(String fileId);

	
}
