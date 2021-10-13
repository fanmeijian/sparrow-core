package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.app.SparrowApp;

@RepositoryRestResource(exported = false)
public interface SparrowAppRepository extends JpaRepository<SparrowApp, String> {

}
