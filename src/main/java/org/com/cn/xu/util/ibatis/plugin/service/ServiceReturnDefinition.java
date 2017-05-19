package org.com.cn.xu.util.ibatis.plugin.service;
/**
 * 服务返回值定义基础类
 * @Title: ServiceReturnDefinition.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public final class ServiceReturnDefinition {
	
	private ThreadLocal<Throwable> _local = new ThreadLocal<Throwable>();
	
	private ParamUnit result;
	
//	private Exception exception;

	public ParamUnit getResult() {
		return result;
	}

	public void setResult(ParamUnit result) {
		this.result = result;
	}

	public Throwable getException() {
//		return exception;
		return _local.get();
	}

	public void setException(Throwable exception) {
//		this.exception = exception;
		_local.set(exception);
	}
	
	public void remove(){
		_local.remove();
		remove(result);
	}
	
	private void remove(ParamUnit params){
		if(params != null){
			params.remove();
			if(params.getChild() != null){
				for(ParamUnit item:params.getChild()){
					remove(item);
				}
			}
		}
	}
}
