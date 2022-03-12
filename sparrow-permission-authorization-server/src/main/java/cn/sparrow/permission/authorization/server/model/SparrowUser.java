package cn.sparrow.permission.authorization.server.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_user")
public class SparrowUser implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String username;

  @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Transient
  private String oldPassword;
  @Transient
  private String secondPassword;

  private byte enabled;

  // uni-directional many-to-many association to SwdSysrole
  @JsonIgnoreProperties("users") // 用来防止无限循环，因为menu里面含user的列表
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "spr_user_sysrole", joinColumns = {@JoinColumn(name = "username")},
      inverseJoinColumns = {@JoinColumn(name = "sysrole_id")})
  private Set<Sysrole> sysroles;
  
  public SparrowUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
