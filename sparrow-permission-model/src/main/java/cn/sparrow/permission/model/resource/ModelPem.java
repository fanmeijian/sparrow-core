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
@Table(name = "spr_model_pem")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Audited
public class ModelPem extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@Include
	@Id
	private ModelPemPK id;

	public ModelPem(ModelPemPK id) {
		this.id = id;
	}

	public ModelPem(String modelId, PermissionTargetEnum target, String targetId, AllowOrDeny type) {
		this.id = new ModelPemPK(modelId, target, targetId, type);
	}

}
