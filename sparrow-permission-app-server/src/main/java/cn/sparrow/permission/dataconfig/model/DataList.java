package cn.sparrow.permission.dataconfig.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sparrow.permission.model.AbstractSparrowUuidEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 12:41:07 PM
 */

@Data
@EqualsAndHashCode( callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_data_list")
public class DataList extends AbstractSparrowUuidEntity implements Serializable{

  private static final long serialVersionUID = 1L;
  private String code;

  private String language;
  private String name;
  private String parentId;


}
