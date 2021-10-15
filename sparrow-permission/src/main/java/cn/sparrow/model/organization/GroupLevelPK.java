package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class GroupLevelPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "level_id")
	private String levelId;

	public GroupLevelPK(){

	}

	public GroupLevelPK(String groupId, String levelId) {
		super();
		this.groupId = groupId;
		this.levelId = levelId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, levelId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupLevelPK other = (GroupLevelPK) obj;
		return Objects.equals(groupId, other.groupId) && Objects.equals(levelId, other.levelId);
	}

}