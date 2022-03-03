package cn.sparrow.permission.service.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.app.SparrowApp;

public interface SparrowAppRepository extends JpaRepository<SparrowApp, String> {

	@Transactional
	void deleteByIdIn(@NotNull String[] ids);

}
