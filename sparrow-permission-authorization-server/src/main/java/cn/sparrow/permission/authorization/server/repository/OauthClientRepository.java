package cn.sparrow.permission.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.authorization.server.model.OauthClient;

public interface OauthClientRepository extends JpaRepository<OauthClient, String> {

}
