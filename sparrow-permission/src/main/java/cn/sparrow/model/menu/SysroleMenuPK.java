package cn.sparrow.model.menu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SysroleMenuPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "SYSROLE_ID", insertable = false, updatable = false)
	private String sysroleId;

	@Column(name = "MENU_ID", insertable = false, updatable = false)
	private String menuId;
}