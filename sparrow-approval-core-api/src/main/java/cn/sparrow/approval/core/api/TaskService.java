package cn.sparrow.approval.core.api;

public interface TaskService {

	public boolean accept(String taskInstanceId);

	public boolean reject(String taskInstanceId);

	public boolean ignore(String taskInstanceId);

	public boolean forward(String taskInstanceId);

	public boolean notice(String taskInstanceId);

	public boolean returnBack(String taskInstanceId);

	public boolean comment(String taskInstanceId);

	public boolean discuss(String taskInstanceId);

	public boolean switchTaskRole(String taskInstanceId);

	public boolean invite(String taskInstanceId);

	public boolean withdraw(String taskInstanceId);
}
