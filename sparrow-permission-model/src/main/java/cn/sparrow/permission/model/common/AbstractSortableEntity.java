package cn.sparrow.permission.model.common;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class AbstractSortableEntity extends AbstractSparrowUuidEntity {
    private String previousNodeId;
    private String nextNodeId;
    private String icon;
}
