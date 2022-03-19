package cn.sparrow.permission.constant;

public enum HttpMethodEnum {
	DELETE, GET, PATCH, POST, PUT;

//	private static final Map<String, HttpMethodEnum> mapping;
//
//	static {
//		Map<String, HttpMethodEnum> operations = new HashMap<>();
//		for (HttpMethodEnum operation : HttpMethodEnum.values()) {
//			operations.put(operation.name(), operation);
//		}
//
//		mapping = Collections.unmodifiableMap(operations);
//	}
//
//	@JsonCreator
//	public static HttpMethodEnum forValue(@NonNull final String name) {
//		HttpMethodEnum candidate = mapping.get(name);
//		if (candidate == null) {
//			throw new IllegalArgumentException("Unable " + name);
//		}
//		return candidate;
//	}
}
