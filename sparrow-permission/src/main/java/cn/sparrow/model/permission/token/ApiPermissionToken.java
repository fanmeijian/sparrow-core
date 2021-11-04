package cn.sparrow.model.permission.token;

import org.springframework.http.HttpMethod;
import cn.sparrow.model.permission.AbstractPermissionToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ApiPermissionToken extends AbstractPermissionToken {
	private String apiPath;
	private HttpMethod method;
}
