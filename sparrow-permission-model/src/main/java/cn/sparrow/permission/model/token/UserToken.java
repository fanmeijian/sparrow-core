package cn.sparrow.permission.model.token;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import net.bytebuddy.asm.Advice.This;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserToken {
	@Include
	private String username;

	private List<String> sysroles;
	private List<String> userGroups;
	private Set<String> sysroleGroups;
	private Set<String> allGroups = new HashSet<>();

	public Set<String> getAllGroups() {
		if (userGroups != null && userGroups.size() > 0) {
			this.allGroups.addAll(this.userGroups);
		}
		if (sysroleGroups != null && sysroleGroups.size() > 0) {
			this.allGroups.addAll(this.sysroleGroups);
		}
		return this.allGroups;
	}
}
