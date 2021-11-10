package cn.sparrow.permission.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.common.ApiPermissionEnum;
import cn.sparrow.model.permission.SparrowApi;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "url-controller")
@RepositoryRestResource(exported = false)
public interface ApiRepository extends JpaRepository<SparrowApi, String> {

  List<SparrowApi> findByClientId(String clientId);
  List<SparrowApi> findByClientIdAndPermission(String clientId, ApiPermissionEnum permission);
  
  @Transactional
  void deleteByIdIn(String[] ids);
  Page<SparrowApi> findByIdIn(String[] ids, Pageable unpaged);
}
