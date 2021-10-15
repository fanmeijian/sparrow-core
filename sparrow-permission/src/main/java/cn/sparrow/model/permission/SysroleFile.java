package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_sysrole_file")
public class SysroleFile extends AbstractOperationLog {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysroleFilePK id;

	public SysroleFile() {
	}

}