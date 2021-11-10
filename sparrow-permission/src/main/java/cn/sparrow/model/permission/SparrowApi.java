package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpMethod;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.common.ApiPermissionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EntityListeners({  AuditingEntityListener.class })
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_api")
public class SparrowApi extends AbstractOperationLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  private String id;
  private String name;
  private String uri;
  private boolean isSystem;

  @Enumerated(EnumType.STRING)
  private HttpMethod method;
  
  @Enumerated(EnumType.STRING)
  private ApiPermissionEnum permission;

  @Column(name = "client_id")
  private String clientId;

//  @OneToMany(mappedBy = "sparrowUrl")
//  Set<UserUrlPermission> userUrlPermissions;

//  @EqualsAndHashCode.Exclude
//  @OneToMany(mappedBy = "sparrowUrl",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  Set<SysroleUrlPermission> sysroleUrPermissions;

//  @ManyToOne
//  @JoinColumn(name = "app_id")
//  private SparrowApp sparrowApp;
  
  public SparrowApi(String name, String uri,HttpMethod method , String clientId, ApiPermissionEnum permission) {
    this.name = name;
    this.uri = uri;
    this.method = method;
    this.clientId = clientId;
    this.permission = permission;
  }

}
