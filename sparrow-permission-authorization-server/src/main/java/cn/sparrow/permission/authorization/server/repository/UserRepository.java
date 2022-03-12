package cn.sparrow.permission.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.authorization.server.model.SparrowUser;

public interface UserRepository extends JpaRepository<SparrowUser, String> {

}
