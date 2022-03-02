package cn.sparrow.permission.model.group;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Setter
@Getter
public class GroupUserPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	private String username;

	public GroupUserPK(){

	}

	public GroupUserPK(String groupId, String username) {
		super();
		this.groupId = groupId;
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupUserPK other = (GroupUserPK) obj;
		return Objects.equals(groupId, other.groupId) && Objects.equals(username, other.username);
	}

}