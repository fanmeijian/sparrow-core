package cn.sparrow.model.app;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.permission.Developer;
import cn.sparrow.model.permission.Menu;
import cn.sparrow.model.permission.SparrowUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_app")
public class SparrowApp extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String code;
	private String remark;
	private String deployId;

	@ManyToOne
	@JoinColumn(name = "developer_id")
	private Developer developer;

//	@ManyToOne
//	@JoinColumn(name = "catalog_id")
//	private Catalog catalog;

//	@OneToMany(targetEntity = Model.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sparrowApp")
//	private List<Model> models;

	@OneToMany(targetEntity = Menu.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sparrowApp")
	private List<Menu> menus;

	@OneToMany(targetEntity = SparrowUrl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sparrowApp")
	private List<SparrowUrl> authorities;

}
