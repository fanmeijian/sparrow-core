package cn.sparrow.permission.model.token;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserToken {
	@Include
	private String username;
	private List<String> sysroles;
	private List<String> groups;
}
