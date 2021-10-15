package cn.sparrow.model.permission;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_file")
@NamedQuery(name = "SprFile.findAll", query = "SELECT s FROM SprFile s")
public class SprFile extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	private String id;

	private String hash;

	private String name;

	private String url;

	public SprFile() {
	}

}