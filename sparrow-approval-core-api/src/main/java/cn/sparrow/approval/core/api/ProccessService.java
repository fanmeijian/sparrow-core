package cn.sparrow.approval.core.api;

public interface ProccessService {
	
	public String start(String proccessId);
	public void terminate(String proccessInstanceId);
	public void end(String proccessInstanceId);
	
}
