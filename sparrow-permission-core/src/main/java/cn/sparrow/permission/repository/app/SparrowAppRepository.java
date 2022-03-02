package cn.sparrow.permission.repository.app;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.app.SparrowApp;

@RepositoryRestResource(exported = false)
public interface SparrowAppRepository extends JpaRepository<SparrowApp, String> {

	@Transactional
	void deleteByIdIn(@NotNull String[] ids);

}
