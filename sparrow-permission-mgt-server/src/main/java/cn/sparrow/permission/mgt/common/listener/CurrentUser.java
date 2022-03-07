package cn.sparrow.permission.mgt.common.listener;

public class CurrentUser {
	public static final CurrentUser INSTANCE = new CurrentUser();

	private static final ThreadLocal<String> storage = new ThreadLocal<>();

	public void logIn(String user) {
		storage.set(user);
	}

	public void logOut() {
		storage.remove();
	}

	public String get() {
		return storage.get();
	}
}
