package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.List;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionExpressionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionExpression<T, ID> implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private PermissionTypeEnum permissionType;
  private PermissionExpressionEnum expression;
  private PermissionEnum permission;
  private ID id;
  private List<ID> ids;
  private String regxOrCustom;
}
