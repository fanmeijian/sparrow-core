package cn.sparrow.permission.service.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.Model;

public interface ModelRepository extends JpaRepository<Model, String> {
	@Transactional
	void deleteByNameIn(String[] ids);

	Page<Model> findByNameIn(@NotNull String[] ids, Pageable pageable);
}
