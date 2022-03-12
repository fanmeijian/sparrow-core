package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_user")
@Audited
public class GroupUser extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	private GroupUserPK id;
	private String stat;

	public GroupUser(GroupUserPK f) {
		this.id = f;
	}

	public GroupUser(String groupId, String username) {
		this.id = new GroupUserPK(groupId, username);
	}

}
