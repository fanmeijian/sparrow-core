package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.organization.PositionLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_org_job_level")
@NamedQueries({
		@NamedQuery(name = "GroupOrgJobLevel.findByOrgJobLevelId", query = "SELECT gojl FROM GroupOrgJobLevel gojl WHERE gojl.id.positionLevelId=:jobLevelId AND gojl.id.orgId=:orgId") })
public class GroupOrgJobLevel extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	@JoinColumns({ @JoinColumn(name = "group_id"), @JoinColumn(name = "position_level_id") })
	private GroupOrgJobLevelPK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "position_level_id", insertable = false, updatable = false)
	private PositionLevel positionLevel;

	public GroupOrgJobLevel(GroupOrgJobLevelPK id) {
		this.id = id;
	}

	public GroupOrgJobLevel(String groupId, String positionLevelId, String orgId) {
		this.id = new GroupOrgJobLevelPK(groupId, positionLevelId,orgId);
	}

}
