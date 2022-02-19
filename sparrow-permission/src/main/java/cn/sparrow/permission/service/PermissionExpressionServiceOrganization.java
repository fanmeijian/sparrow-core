package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.organization.repository.OrganizationRepository;
import cn.sparrow.organization.service.OrganizationService;

@Service
public class PermissionExpressionServiceOrganization {
	@Autowired
	OrganizationService organizationService;
	@Autowired
	OrganizationRepository organizationRepository;

	public boolean evaluate(String id, PermissionExpression<?> permissionExpression) {
		switch (permissionExpression.getExpression()) {
		case IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			}
		case NOT_IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			}
		case IS_ABOVE:
			if (organizationService.getParents(permissionExpression.getIds().get(0).toString())
					.contains(organizationRepository.findById(id).get())) {
				return true;
			}
		case IS_AND_ABOVE:
			if (organizationService.getParents(permissionExpression.getIds().get(0).toString())
					.contains(organizationRepository.findById(id).get())
					|| permissionExpression.getIds().get(0).toString().equals(id)) {
				return true;
			}
		case IS_BELOW:
			if (organizationService.getChildren(permissionExpression.getIds().get(0).toString())
					.contains(organizationRepository.findById(id).get())) {
				return true;
			}
		case IS_AND_BELOW:
			if (organizationService.getChildren(permissionExpression.getIds().get(0).toString())
					.contains(organizationRepository.findById(id).get())
					|| permissionExpression.getIds().get(0).toString().equals(id)) {
				return true;
			}
		default:
			break;
		}
		return false;
	}

}
