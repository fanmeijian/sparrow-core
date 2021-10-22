package cn.sparrow.permission.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.Model;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "model-controller")
@RepositoryRestResource(exported = false)
public interface ModelRepository extends JpaRepository<Model, String> {
	@Transactional
	void deleteByNameIn(String[] ids);

	Page<Model> findByNameIn(@NotNull String[] ids, Pageable pageable);
}
