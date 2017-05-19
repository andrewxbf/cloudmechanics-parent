package org.com.cn.xu.util.ibatis.plugin.service;

import java.lang.reflect.Method;

/**
 * 远程服务定义基础类
 * @Title: ServiceDefinition.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public final class ServiceDefinition {
	/**
	 * 接口名
	 */
	private String interfaceName;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 接口类型
	 */
	private Class iterface;
	/**
	 * 方法
	 */
	private Method method;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 分组
	 */
	private String group;
	/**
	 * 远程服务ID
	 */
	private Long serviceId;
	/**
	 * 验证session键
	 */
	private String validSession;
	/**
	 * 请求参数定义
	 */
	private ServiceParametersDefinition serviceParamDefinition;
	/**
	 * 返回值定义
	 */
	private ServiceReturnDefinition serviceReturnDefinition;
	
	public ServiceParametersDefinition getServiceParamDefinition() {
		return serviceParamDefinition;
	}
	public void setServiceParamDefinition(ServiceParametersDefinition serviceParamDefinition) {
		this.serviceParamDefinition = serviceParamDefinition;
	}
	public ServiceReturnDefinition getServiceReturnDefinition() {
		return serviceReturnDefinition;
	}
	public void setServiceReturnDefinition(ServiceReturnDefinition serviceReturnDefinition) {
		this.serviceReturnDefinition = serviceReturnDefinition;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class getIterface() {
		return iterface;
	}
	public void setIterface(Class iterface) {
		this.iterface = iterface;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void remove(){
		if(serviceParamDefinition != null){
			serviceParamDefinition.remove();
		}
		if(serviceReturnDefinition != null){
			serviceReturnDefinition.remove();
		}
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getValidSession() {
		return validSession;
	}
	public void setValidSession(String validSession) {
		this.validSession = validSession;
	}
}
