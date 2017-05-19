package org.com.cn.xu.util.ibatis.plugin.annotation;
/**
 * 生命周期定义,none请求是无用，返回时销毁session。
 * @Title: Scope.java
 * @Package com.framework.servlet.scope
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public enum Scope {
	none,request,session,application,cookie
}
