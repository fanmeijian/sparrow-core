package cn.sparrow.permission.model.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserFilePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private String username;

	@Column(name = "FILE_ID", insertable = false, updatable = false)
	private String fileId;

	private String permission;

	public UserFilePK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFilePK)) {
			return false;
		}
		UserFilePK castOther = (UserFilePK) other;
		return this.username.equals(castOther.username) && this.fileId.equals(castOther.fileId)
				&& this.permission.equals(castOther.permission);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.fileId.hashCode();
		hash = hash * prime + this.permission.hashCode();

		return hash;
	}
}