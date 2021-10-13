package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.common.OperationLog;

/***
 * 
 * @author fanmj
 * 
 * 不能放repository包里面，因为会出现日志类循环调用的死循环。因此只能放根包里
 *
 */
@RepositoryRestResource(exported = false)
public interface OperationLogRepository extends JpaRepository<OperationLog, String> {

}
