package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.file.UserFile;
import cn.sparrow.model.file.UserFilePK;

@RepositoryRestResource(exported = false)
public interface UserFileRepository extends JpaRepository<UserFile, UserFilePK> {

	List<UserFile> findByIdFileId(String fileId);

}
