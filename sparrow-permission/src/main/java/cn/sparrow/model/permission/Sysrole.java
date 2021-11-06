package cn.sparrow.model.permission;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.group.GroupSysrole;
import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.ReadPermissionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole")
@EntityListeners( AuditLogListener.class)
public class Sysrole extends AbstractOperationLog {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;
	
	private String name;
	@Column(unique = true)
	private String code;
	private boolean isSystem;

	@EqualsAndHashCode.Exclude
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, targetEntity = SysroleMenu.class, cascade = CascadeType.ALL, mappedBy = "sysrole")
	private Set<SysroleMenu> sysroleMenus;
	
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	@OneToMany(targetEntity = GroupSysrole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysrole")
	private Set<GroupSysrole> groupSysroles;
	
//	@EqualsAndHashCode.Exclude
//	@JsonIgnore
//	@OneToMany(targetEntity = SysroleUrlPermission.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysrole")
//	private Set<SysroleUrlPermission> sysroleUrlPermissions;

	public Sysrole(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
}
