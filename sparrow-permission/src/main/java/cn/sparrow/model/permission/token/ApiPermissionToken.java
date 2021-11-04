package cn.sparrow.model.permission.token;

import java.util.List;

import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPermissionToken {
	private String apiPath;
	private HttpMethod method;
	private List<PermissionExpression> permissionExpressions;
}
