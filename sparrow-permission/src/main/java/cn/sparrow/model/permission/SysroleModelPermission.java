package cn.sparrow.model.permission;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: SysroleModelPermission
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_model_permission")
@EntityListeners(AuditingEntityListener.class)
public class SysroleModelPermission extends AbstractOperationLog implements Serializable {

	public SysroleModelPermission(SysroleModelPermissionPK f) {
		this.id = f;
	}

	@EmbeddedId
	private SysroleModelPermissionPK id;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
	private Sysrole sysrole; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_name", insertable = false, updatable = false)
	private Model model; 

}
