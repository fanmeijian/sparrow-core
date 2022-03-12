package cn.sparrow.approval.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "spr_node_instance")
public class NodeInstance{
	@EqualsAndHashCode.Include
	@Id
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "proccess_instance_id")
	private ProccessInstance proccessInstance;
	
	@ManyToOne
	@JoinColumn(name = "node_id")
	private Node node;
	
	
}