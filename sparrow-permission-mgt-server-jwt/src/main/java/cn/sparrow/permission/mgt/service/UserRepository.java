package cn.sparrow.permission.mgt.service;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.mgt.model.SparrowUser;

public interface UserRepository extends JpaRepository<SparrowUser, String> {

}
