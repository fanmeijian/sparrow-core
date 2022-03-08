package cn.sparrow.approval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_node_instance_variable")
public class NodeInstanceVariable{
	@EqualsAndHashCode.Include
	@Id
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "node_id")
	private String nodeId;
	
	@ManyToOne
	@JoinColumn(name = "node_id")
	private NodeInstance nodeInstance;
	
	private String name;
	@Lob
	private String value;
	@Enumerated(EnumType.STRING)
	private VariableDirectionEnum direction;
}