package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.common.OperationLog;

/***
 * 
 * @author fanmj
 * 
 * 不能放repository包里面，因为会出现日志类循环调用的死循环。因此只能放根包里
 *
 */

public interface OperationLogRepository extends JpaRepository<OperationLog, String> {

}
