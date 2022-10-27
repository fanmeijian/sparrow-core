package cn.sparrow.permission.model.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import cn.sparrow.permission.model.common.BaseLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "SPR_APP")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class App extends BaseLog {

    private static final long serialVersionUID = 1L;
    @Include
    @Id
    @GenericGenerator(name = "id-generator", strategy = "uuid")
    @GeneratedValue(generator = "id-generator")
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    
    private String name;
    
    private String workspaceId;
}
