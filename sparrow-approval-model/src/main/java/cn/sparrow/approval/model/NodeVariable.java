package cn.sparrow.approval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "spr_node_variable")
public class NodeVariable{
	@EqualsAndHashCode.Include
	@Id
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "node_id")
	private String nodeId;
	
	@MapsId
	@ManyToOne
	@JoinColumn(name = "node_id")
	private Node node;
	private String name;
	private String proccessVariableName;
	
	@Enumerated(EnumType.STRING)
	private VariableDirectionEnum direction;
}