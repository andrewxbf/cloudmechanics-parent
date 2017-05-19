package org.com.cn.xu.util.ibatis.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务参数定义注解
 * @Title: Param.java
 * @Package com.framework.servlet.request
 * @Description:用于描述如何从http请求中解析对应的参数
 * @author maxwell
 * @version V1.0
 * @date
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER,ElementType.FIELD })
public @interface Param {
	public String name();
	public String group() default "";
	public Class target() default Object.class;
	public Scope scope() default Scope.request;
}
