package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EntityListeners({  AuditingEntityListener.class })
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "spr_model")
public class Model extends AbstractOperationLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private String nameTxt;
	private String remark;

//	@ManyToOne
//	@JoinColumn(name = "catalog_id")
//	private Catalog catalog;

//	@ManyToOne
//	@JoinColumn(name = "app_id")
//	private SparrowApp sparrowApp;

	@OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
	private List<ModelAttribute> modelAttributes;

//	@OneToMany(targetEntity = ModelReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
//	private List<ModelReader> modelReaders; // 可读此模型的用户列表
//
//	@OneToMany(targetEntity = ModelAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
//	private List<ModelAuthor> modelAuthors; // 可创建此模型数据的用户列表
//
//	@OneToMany(targetEntity = ModelDenyReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
//	private List<ModelDenyReader> modelDenyReaders; // 模型的拒绝读用户列表
//
//	@OneToMany(targetEntity = ModelDenyAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
//	private List<ModelDenyAuthor> modelDenyAuthors; // 模型的拒绝读用户列表
//
//	@OneToMany(targetEntity = DataDenyReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
//	private List<DataDenyReader> dataDenyReaders; // 模型的拒绝读用户列表

	public Model(String name) {
		super();
		this.name = name;
	}

	public Model() {
		super();
	}

	// private String modelReaderEL; // 可读取此模型的表达式
	// private String modelDenyReaderEL; // 拒绝读此模型的表达式
	// private String modelAuthorEL; // 可创建此模型数据的表达式
	// private String modelDenyAuthorEL; // 拒绝创建模型数据的表达式

}
