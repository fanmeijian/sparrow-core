package cn.sparrow.dataconfig.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.dataconfig.model.DataList;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 12:03:09 PM
 */

//@RepositoryRestResource(exported = false)
public interface DataListRepository extends JpaRepository<DataList, String> {

  List<DataList> findByParentId(String parentId);
  DataList findByCode(String code);
}
