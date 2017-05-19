package org.com.cn.xu.util.ibatis.plugin.service;
/**
 * 远程服务定义接口
 * @Title: IServiceDefinitionFactory.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public interface IServiceDefinitionFactory {
	/**
	 * 查找远程服务对象
	 * @param interfacename 接口全名
	 * @param method 方法名
	 * @return 远程服务对象
	 */
	public ServiceDefinition traceService(String interfacename, String method);
	/**
	 * 查找远程服务对象
	 * @param interfacename 接口全名
	 * @param method 方法名
	 * @param version 服务版本
	 * @return 远程服务对象
	 */
	public ServiceDefinition traceService(String interfacename, String method, String version);
	/**
	 * 查找远程服务对象
	 * @param interfacename 接口全名
	 * @param method 方法名
	 * @param version 服务版本
	 * @param group 服务分组
	 * @return 远程服务对象
	 */
	public ServiceDefinition traceService(String interfacename, String method, String version, String group);
	/**
	 * 查找远程服务对象
	 * @param serviceId 远程服务ID
	 * @param method 方法名
	 * @param version 服务版本
	 * @param group 服务分组
	 * @return 远程服务对象
	 */
	public ServiceDefinition traceService(Long serviceId, String method, String version, String group);
	/**
	 * 初始化远程服务Bean
	 * @param serviceId 远程服务ID
	 * @param interfacecls 远程服务接口
	 * @param version 版本
	 * @param group 分组
	 */
	public void initBeanService(Long serviceId, Class interfacecls, String version, String group);
	/**
	 * 初始化远程服务Bean
	 * @param interfacecls 远程服务接口
	 * @param version 版本
	 * @param group 分组
	 */
	public void initBeanService(Class interfacecls, String version, String group);
}
