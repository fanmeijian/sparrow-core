package cn.sparrow.model.permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.SerializationUtils;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.permission.token.DataPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "spr_permission_token")
public class PermissionToken extends AbstractOperationLog {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  @EqualsAndHashCode.Include
  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  protected String id;


  @Lob
  @Column(name = "data_permission_token")
  private byte[] dataPermissionTokenByteArray;

  public DataPermissionToken getDataPermissionToken() {
    return (DataPermissionToken) SerializationUtils.deserialize(dataPermissionTokenByteArray);
  }

  public void setDataPermissionToken(DataPermissionToken dataPermissionToken) {
    this.dataPermissionTokenByteArray = SerializationUtils.serialize(dataPermissionToken);
  }

}
