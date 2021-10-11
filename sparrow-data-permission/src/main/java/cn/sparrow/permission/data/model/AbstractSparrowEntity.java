package cn.sparrow.permission.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import cn.sparrow.permission.data.listener.AbstractEntityListener;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AbstractEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class AbstractSparrowEntity extends AbstractOperationLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;

	// 数据及模型用户权限
//	@Transient
//	@OneToMany(targetEntity = DataReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<DataReader> dataReaders; // 可读此行数据的用户列表
//
//	@Transient
//	@OneToMany(targetEntity = DataAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<DataAuthor> dataAuthors; // 可编辑此行数据用户的列表
//
//	@Transient
//	@OneToMany(targetEntity = DataDenyReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<DataDenyReader> dataDenyReaders; // 拒绝读此行数据的用户列表
//
//	@Transient
//	@OneToMany(targetEntity = DataDenyAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<DataDenyAuthor> dataDenyAuthors; // 拒绝写此行数据的用户列表

//	@OneToOne
//	@JsonIgnore
//	private Model model;

	@Transient
	@Size(max = 0)
	private List<String> errorMessage = new ArrayList<String>();

	// 数据及模型的权限表达式
//	private String readerEL; // 可读此行数据的表达式
//	private String denyReaderEL; // 拒绝此行数据的表达式
//	private String authorEL; // 可编辑此行数据的表达式
//	private String denyAuthorEL; // 可编辑此行数据的表达式

	// #TODO
	// private Map<String, String> fieldReaderEL; // key为字段名；字段的读者权限表达式
	// private Map<String, String> fieldAuthorEL; // key为字段名；字段的作者权限表达式
	// private Map<String, String> fieldDenyReaderEL; // key为字段名；字段的读者拒绝表达式
	// private Map<String, String> fieldDenyAtuhorEL; // key为字段名；字段的作者拒绝表达式
	//


	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractSparrowEntity that = (AbstractSparrowEntity) obj;

		return this.id.equals(that.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
