package cn.sparrow.permission.common.configuration;

import java.util.Collections;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

public class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {
	//解决principal里默认读取token里面的sub值，将其改为读取user_name，因为spring authorization server产生的
	//token不含sub字段
	private final MappedJwtClaimSetConverter delegate =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());
	
	@Override
	public Map<String, Object> convert(Map<String, Object> claims) {
		Map<String, Object> convertedClaims = this.delegate.convert(claims);

        String username = (String) convertedClaims.get("user_name");
        convertedClaims.put("sub", username);

        return convertedClaims;
	}

}
