package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.app.SparrowApp;

public interface SparrowAppRepository extends JpaRepository<SparrowApp, String> {

}
