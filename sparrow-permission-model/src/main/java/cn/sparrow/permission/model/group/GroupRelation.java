package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import org.hibernate.envers.Audited;

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

	@Audited
	@EmbeddedId
	private GroupRelationPK id;

	public GroupRelation(String groupId, String parentId) {
		this.id = new GroupRelationPK(groupId, parentId);
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getGroupId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}

}
