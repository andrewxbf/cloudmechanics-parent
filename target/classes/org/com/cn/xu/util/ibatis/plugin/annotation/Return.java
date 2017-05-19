package org.com.cn.xu.util.ibatis.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义服务接口返回结果注解
 * @Title: Return.java
 * @Package com.framework.servlet.response
 * @Description:将以此注解定义的响应参数名格式化成对应的响应类型格式。
 * @author maxwell
 * @version V1.0
 * @date
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD,ElementType.FIELD})
public @interface Return {
	public String name() default "";
	public Class target() default Object.class;
	public String group() default "";
	public Scope scope() default Scope.request;
}
