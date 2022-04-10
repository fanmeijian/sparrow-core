package cn.sparrow.permission.model.group;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRolePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "role_id")
	private String roleId;
	// 用来确定岗位所属的组织，如果要适用所有的组织，设置为root
	private String orgId = "root";
	
	public GroupRolePK(String groupId, String roleId) {
		this.groupId = groupId;
		this.roleId = roleId;
	}

}