package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpMethod;
import cn.sparrow.model.app.SparrowApp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_url")
public class SparrowUrl extends AbstractOperationLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  private String id;

  private String name;

  private String uri;

  @Enumerated(EnumType.STRING)
  private HttpMethod method;
  
  @Enumerated(EnumType.STRING)
  private UrlPermissionEnum permission;

  @Column(name = "client_id")
  private String clientId;

//  @OneToMany(mappedBy = "sparrowUrl")
//  Set<UserUrlPermission> userUrlPermissions;

  @OneToMany(mappedBy = "sparrowUrl")
  Set<SysroleUrlPermission> sysroleUrPermissions;

  @ManyToOne
  @JoinColumn(name = "app_id")
  private SparrowApp sparrowApp;
  
  public SparrowUrl(String name, String uri,HttpMethod method , String clientId, UrlPermissionEnum permission) {
    this.name = name;
    this.uri = uri;
    this.method = method;
    this.clientId = clientId;
    this.permission = permission;
  }

}
