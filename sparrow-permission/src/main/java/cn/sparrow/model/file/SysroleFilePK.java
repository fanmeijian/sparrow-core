package cn.sparrow.model.file;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SysroleFilePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "SYSROLE_ID", insertable = false, updatable = false)
	private String sysroleId;

	@Column(name = "FILE_ID", insertable = false, updatable = false)
	private String fileId;

	private String permission;

	public SysroleFilePK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysroleFilePK)) {
			return false;
		}
		SysroleFilePK castOther = (SysroleFilePK) other;
		return this.sysroleId.equals(castOther.sysroleId) && this.fileId.equals(castOther.fileId)
				&& this.permission.equals(castOther.permission);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sysroleId.hashCode();
		hash = hash * prime + this.fileId.hashCode();
		hash = hash * prime + this.permission.hashCode();

		return hash;
	}
}