package cn.sparrow.permission.model.group;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GroupPositionLevelPK implements Serializable {

	public GroupPositionLevelPK(String groupId, String positionLevelId) {
		this.groupId = groupId;
		this.positionLevelId = positionLevelId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "position_level_id")
	private String positionLevelId;
	private String orgId = "root";

}