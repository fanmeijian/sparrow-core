package cn.sparrow.model.group;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class GroupRolePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "role_id")
	private String roleId;

	public GroupRolePK(){

	}

	public GroupRolePK(String groupId, String roleId) {
		super();
		this.groupId = groupId;
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupRolePK other = (GroupRolePK) obj;
		return Objects.equals(groupId, other.groupId) && Objects.equals(roleId, other.roleId);
	}

}