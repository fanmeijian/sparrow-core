package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.SparrowFile;

@RepositoryRestResource(exported = false)
public interface FileRepository extends JpaRepository<SparrowFile, String> {

//	@Query("SELECT s FROM SprFile s,UserFile uf WHERE s.id = uf.id.fileId AND uf.id.permission = 'LIST' AND uf.id.username IN (?1,'ANONYMOUSE','AUTHENTICATED')")
//	List<SparrowFile> listByPermission(String username);
//	
//	@Query("SELECT s FROM SprFile s,UserFile uf WHERE s.id = uf.id.fileId AND uf.id.permission = 'DOWNLOAD' AND uf.id.username IN (?1,'ANONYMOUSE','AUTHENTICATED') AND s.id=?2")
//	SparrowFile getByUsernameAndFileId(String username,String fileId);
}
