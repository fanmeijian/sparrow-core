package cn.sparrow.permission.model.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScopeApiPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "scope_id")
	private String scopeId;
	@Column(name = "api_id")
	private String apiId;

}
