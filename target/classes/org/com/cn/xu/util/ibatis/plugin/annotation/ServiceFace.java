package org.com.cn.xu.util.ibatis.plugin.annotation;

import java.lang.annotation.*;

/**
 * 服务接口发布描述注解
 * @Title: ServiceFace.java
 * @Package com.framework.servlet.service
 * @Description:用于描述接口以哪个ID,版本，分组进行发布
 * @author maxwell
 * @version V1.0
 * @date
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE})
@Inherited
public @interface ServiceFace {
	public long id();
	public String version() default "";
	public String group() default "";
}
