package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.permission.listener.AuditLogListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EntityListeners({AuditLogListener.class})
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_model")
public class Model extends AbstractOperationLog implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  private String name;
  private String nameTxt;
  private String remark;
  private boolean isSystem;
  @Column(name = "app_id")
  private String appId;

  // @ManyToOne
  // @JoinColumn(name = "catalog_id")
  // private Catalog catalog;

   @ManyToOne
   @JoinColumn(name = "app_id",insertable = false, updatable = false)
   private SparrowApp sparrowApp;

  @OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
  private List<ModelAttribute> modelAttributes;

  public Model(String name) {
    this.name = name;
  }
  
  public Model(String name, boolean isSystem) {
	    this.name = name;
	    this.isSystem = isSystem;
	  }
  
  // @OneToMany(targetEntity = ModelReader.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
  // mappedBy = "model")
  // private List<ModelReader> modelReaders; // 可读此模型的用户列表
  //
  // @OneToMany(targetEntity = ModelAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
  // mappedBy = "model")
  // private List<ModelAuthor> modelAuthors; // 可创建此模型数据的用户列表
  //
  // @OneToMany(targetEntity = ModelDenyReader.class, cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY, mappedBy = "model")
  // private List<ModelDenyReader> modelDenyReaders; // 模型的拒绝读用户列表
  //
  // @OneToMany(targetEntity = ModelDenyAuthor.class, cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY, mappedBy = "model")
  // private List<ModelDenyAuthor> modelDenyAuthors; // 模型的拒绝读用户列表
  //
  // @OneToMany(targetEntity = DataDenyReader.class, cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY, mappedBy = "model")
  // private List<DataDenyReader> dataDenyReaders; // 模型的拒绝读用户列表


}
