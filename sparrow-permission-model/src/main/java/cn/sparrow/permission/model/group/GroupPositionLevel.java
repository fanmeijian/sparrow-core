package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "spr_group_position_level")
public class GroupPositionLevel extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	@JoinColumns({ @JoinColumn(name = "group_id"), @JoinColumn(name = "position_level_id") })
	private GroupPositionLevelPK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "position_level_id", insertable = false, updatable = false)
	private PositionLevel positionLevel;

	public GroupPositionLevel(GroupPositionLevelPK id) {
		this.id = id;
	}

	public GroupPositionLevel(String groupId, String positionLevelId, String orgId) {
		this.id = new GroupPositionLevelPK(groupId, positionLevelId, orgId);
	}
	
	public GroupPositionLevel(String groupId, String positionLevelId) {
		this.id = new GroupPositionLevelPK(groupId, positionLevelId);
	}

}
