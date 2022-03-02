package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.repository.organization.OrganizationRelationRepository;
import cn.sparrow.permission.repository.organization.OrganizationRepository;

@Service
public class OrganizationHelper {
	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	OrganizationRelationRepository organizationRelationRepository;

	public boolean isAbove(String ancestorId, String orgId) {
		boolean flag = false;
		for (OrganizationRelation orgRelation : organizationRelationRepository.findByIdOrganizationId(orgId)) {
			if (ancestorId.equals(orgRelation.getId().getParentId()))
				return true;
			else {
				flag = flag || isAbove(ancestorId, orgRelation.getId().getParentId());
			}
		}
		return flag;
	}
	
	public boolean isBelow(String descendantsId, String orgId) {
		boolean flag = false;
		for (OrganizationRelation orgRelation : organizationRelationRepository.findByIdParentId(orgId)) {
			if (descendantsId.equals(orgRelation.getId().getOrganizationId()))
				return true;
			else {
				flag = flag || isBelow(descendantsId, orgRelation.getId().getOrganizationId());
			}
		}
		return flag;
	}
}
