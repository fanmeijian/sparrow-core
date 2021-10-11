package cn.sparrow.model.menu;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class SysroleMenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SYSROLE_ID", insertable=false, updatable=false)
	private String sysroleId;

	@Column(name="MENU_ID", insertable=false, updatable=false)
	private String menuId;


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysroleMenuPK)) {
			return false;
		}
		SysroleMenuPK castOther = (SysroleMenuPK)other;
		return 
			this.sysroleId.equals(castOther.sysroleId)
			&& this.menuId.equals(castOther.menuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sysroleId.hashCode();
		hash = hash * prime + this.menuId.hashCode();
		
		return hash;
	}
}