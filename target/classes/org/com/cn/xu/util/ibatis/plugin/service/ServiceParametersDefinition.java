package org.com.cn.xu.util.ibatis.plugin.service;
/**
 * 服务请求参数定义基础类
 * @Title: ServiceParametersDefinition.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public final class ServiceParametersDefinition {
	
	private ParamUnit[] params;

	public ParamUnit[] getParams() {
		return params;
	}

	public void setParams(ParamUnit[] params) {
		this.params = params;
	}
	
	public void remove(){
		remove(params);
	}
	
	private void remove(ParamUnit[] params){
		if(params != null){
			for(ParamUnit item:params){
				item.remove();
				remove(item.getChild());
			}
		}
	}
}
