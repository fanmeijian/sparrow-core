package cn.sparrow.model.permission;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_file")
public class SparrowFile extends AbstractSparrowUuidEntity {
	private static final long serialVersionUID = 1L;

	private String hash;

	private String name;
	
	private String fileName;

	private String url;

	private ModelAttributePK modelAttributePK;
}