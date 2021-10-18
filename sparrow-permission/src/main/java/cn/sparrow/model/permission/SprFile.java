package cn.sparrow.model.permission;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "spr_file")
public class SprFile extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	private String hash;

	private String name;

	private String url;
}