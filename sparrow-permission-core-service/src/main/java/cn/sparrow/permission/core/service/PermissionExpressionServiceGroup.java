package cn.sparrow.permission.core.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.token.PermissionExpression;

public class PermissionExpressionServiceGroup {

	private EntityManager entityManager;

	public boolean evaluate(String id, PermissionExpression<?> permissionExpression) {
		Set<String> expandedGroups = new HashSet<>();
		permissionExpression.getIds().forEach(f -> {
			expandGroup(f.toString(), expandedGroups);
		});

		switch (permissionExpression.getExpression()) {
		case IN:
			if (expandedGroups.contains(id)) {
				return true;
			}
			break;
		case NOT_IN:
			if (expandedGroups.contains(id)) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	public PermissionExpressionServiceGroup(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PermissionExpressionServiceGroup() {

	}

	// 展开组
	private void expandGroup(String groupId, Set<String> groups) {
		groups.add(groupId);
		entityManager.createNamedQuery("GroupRelation.findByParentId", GroupRelation.class)
				.setParameter("parentId", groupId).getResultList().forEach(groupRelation -> {
					if (!groups.contains(groupRelation.getId().getGroupId())) {
						expandGroup(groupRelation.getId().getGroupId(), groups);
					}
				});
	}

}
