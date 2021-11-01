package cn.sparrow.model.common;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可排序的实体bean
 * 
 * @author fansword
 *
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractSparrowSortableEntity<ID> extends AbstractOperationLog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ID previousNodeId;
	protected ID nextNodeId;
}
