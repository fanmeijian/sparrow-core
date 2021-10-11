package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.file.UserFile;
import cn.sparrow.model.file.UserFilePK;

public interface UserFileRepository extends JpaRepository<UserFile, UserFilePK> {

	List<UserFile> findByIdFileId(String fileId);

}
