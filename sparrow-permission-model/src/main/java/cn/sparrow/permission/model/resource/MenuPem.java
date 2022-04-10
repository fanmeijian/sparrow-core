package cn.sparrow.permission.model.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import cn.sparrow.permission.constant.AllowOrDeny;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spr_menu_pem")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Audited
public class MenuPem extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@Include
	@Id
	private MenuPemPK id;

	public MenuPem(MenuPemPK id) {
		this.id = id;
	}

	public MenuPem(String menuId, PermissionTargetEnum target, String targetId, AllowOrDeny type) {
		this.id = new MenuPemPK(menuId, target, targetId, type);
	}

}
