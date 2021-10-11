package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.User;

public interface UserRepostiroy extends JpaRepository<User, String> {

  
  //?#{ principal?.id }
}
