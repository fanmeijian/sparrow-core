package cn.sparrow.permission.service;

import java.io.Serializable;
import java.util.List;

import cn.sparrow.model.common.PermissionExpressionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionExpression<ID> implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private PermissionExpressionEnum expression;
  private List<ID> ids;
}
