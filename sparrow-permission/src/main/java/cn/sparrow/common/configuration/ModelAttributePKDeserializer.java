package cn.sparrow.common.configuration;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.model.permission.ModelAttributePK;
import cn.sparrow.permission.service.PermissionToken;

public class ModelAttributePKDeserializer extends KeyDeserializer{

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
		return new ObjectMapper().readValue(key, new TypeReference<HashMap<ModelAttributePK, PermissionToken>>() {});
	}


}
