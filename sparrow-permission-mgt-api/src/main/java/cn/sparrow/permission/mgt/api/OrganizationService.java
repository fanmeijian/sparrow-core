package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import cn.sparrow.permission.mgt.api.scopes.OrgScope;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationRole;

public interface OrganizationService extends OrganizationRestService,OrgScope {

	public List<Organization> getChildren(@PathVariable("organizationId") String organizationId);

	public Page<OrganizationRole> getRoles(@PathVariable("organizationId") String organizationId, Pageable pageable);

	public Page<OrganizationPositionLevel> getLevels(@PathVariable("organizationId") String organizationId,
			Pageable pageable);

	public Page<OrganizationGroup> getGroups(@PathVariable("organizationId") String organizationId, Pageable pageable);

	public Page<Employee> getEmployees(@PathVariable("organizationId") String organizationId, Pageable pageable);

}
