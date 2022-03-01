package cn.sparrow.model.permission;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SparrowFunctionPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String packageName;
	private String name;
	private String returnType;
	private String parameters;
}
