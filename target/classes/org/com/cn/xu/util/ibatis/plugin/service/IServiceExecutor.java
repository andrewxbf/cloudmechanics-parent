package org.com.cn.xu.util.ibatis.plugin.service;
/**
 * 远程服务执行器接口
 * @Title: IServiceExecutor.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public interface IServiceExecutor {
	/**
	 * 通过java返回执行远程服务对象中的方法
	 * @param serviceDefinition 远程服务定义
	 * @return 服务返回值对象
	 */
	public ServiceReturnDefinition execute(ServiceDefinition serviceDefinition);
}
