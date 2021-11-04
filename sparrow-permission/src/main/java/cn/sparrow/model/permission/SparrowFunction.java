package cn.sparrow.model.permission;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "spr_function")
public class SparrowFunction {
	@EmbeddedId
	private SparrowFunctionPK id;
	
	@Transient
	@EqualsAndHashCode.Exclude
	private List<SparrowFunctionParameter> parameters;
}
