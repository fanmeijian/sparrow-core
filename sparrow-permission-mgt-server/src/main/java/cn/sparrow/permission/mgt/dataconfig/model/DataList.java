package cn.sparrow.permission.mgt.dataconfig.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

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
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_data_list")
public class DataList implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	@Audited
	private String id;
	
	private String code;

	private String language;
	private String name;
	private String parentId;

}
