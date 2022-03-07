package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_group_relation")
public class GroupRelation extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupRelationPK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", insertable = false, updatable = false)
	private Group parentGroup;

	public GroupRelation(GroupRelationPK id) {
		this.id = id;
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getGroupId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}

}
