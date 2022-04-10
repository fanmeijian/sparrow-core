package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupScope;
import cn.sparrow.permission.model.group.GroupScopePK;

public interface GroupScopeRepository extends JpaRepository<GroupScope, GroupScopePK> {

}
