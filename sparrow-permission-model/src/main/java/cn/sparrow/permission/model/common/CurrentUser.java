package cn.sparrow.permission.model.common;

public class CurrentUser {
//	public static final CurrentUser INSTANCE = new CurrentUser();

	private static final ThreadLocal<String> storage = new ThreadLocal<>();

	public static void logIn(String user) {
		storage.set(user);
	}

	public static void logOut() {
		storage.remove();
	}

	public static String get() {
		return storage.get();
	}
}
