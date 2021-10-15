package cn.sparrow.model.organization;

import java.util.List;

import cn.sparrow.model.common.GroupTypeEnum;
import lombok.Data;

@Data
public class GroupMember {
	private GroupTypeEnum groupType;
	private List<?> members;
}
