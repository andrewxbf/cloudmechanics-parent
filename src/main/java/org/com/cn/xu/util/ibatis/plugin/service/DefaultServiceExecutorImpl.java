package org.com.cn.xu.util.ibatis.plugin.service;

/**
 * 远程服务执行器接口默认实现
 * @Title: DefaultServiceExecutorImpl.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class DefaultServiceExecutorImpl implements IServiceExecutor {
	
//	private final String MSG_FILE = "lang:com.framework.servlet.server.Servlets.properties$";
	/*
	 * 查看接口的注释描述
	 * @see com.framework.servlet.service.IServiceExecutor#execute(com.framework.servlet.service.ServiceDefinition)
	 */
	@Override
	public ServiceReturnDefinition execute(ServiceDefinition serviceDefinition) {
		return null;
		/*
		try{
			Object bean = FApplicationContext.getApplicationContext().getBean(serviceDefinition.getInterfaceName());
			if(bean == null) throw new SystemException(MSG_FILE+"017",ErrorCode.ERROR_CODE_SERVICE_NOTFOUND);
			Object[] pvalues = new Object[0];
			ParamUnit[] params = serviceDefinition.getServiceParamDefinition().getParams();
			if(params != null && params.length>0){
				pvalues= new Object[params.length];
				for(int i=0;i<params.length;i++){
					pvalues[i] = params[i].getValue();
				}
			}
			//Object rs = ReflectionUtils.invokeMethod(serviceDefinition.getMethod(), bean, pvalues);
			//serviceDefinition.getServiceReturnDefinition().getResult().setValue(rs);
		}catch(Throwable ex){
			ex = ExceptionUtils.getCause(ex);
			serviceDefinition.getServiceReturnDefinition().setException(ex);
		}
		return serviceDefinition.getServiceReturnDefinition();
	*/}
}
