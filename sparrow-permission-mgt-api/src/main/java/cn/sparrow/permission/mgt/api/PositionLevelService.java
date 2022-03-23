package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

public interface PositionLevelService extends JobLevelRestService{

	public void addRelation(@RequestBody List<OrganizationPositionLevelRelation> organizationLevelRelations);

	public void removeRelation(@RequestBody List<OrganizationPositionLevelRelationPK> ids);

}
