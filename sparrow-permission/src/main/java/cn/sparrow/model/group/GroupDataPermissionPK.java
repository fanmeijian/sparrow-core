package cn.sparrow.model.group;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import cn.sparrow.model.permission.AbstractDataPermissionPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GroupDataPermissionPK extends AbstractDataPermissionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length = 32)
	private String groupId;
	
}
