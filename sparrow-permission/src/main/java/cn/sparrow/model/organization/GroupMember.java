package cn.sparrow.model.organization;

import java.util.List;

import lombok.Data;

@Data
public class GroupMember {
	private GroupTypeEnum groupType;
	private List<?> members;
}
