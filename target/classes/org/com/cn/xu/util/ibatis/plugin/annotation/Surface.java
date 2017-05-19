package org.com.cn.xu.util.ibatis.plugin.annotation;

import java.lang.annotation.*;

/**
 * 服务接口发布描述注解，功能同@ServiceFace，无需致命服务ID来进行发布.
 * @Title: Surface.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE})
@Inherited
public @interface Surface {
	public String version() default "";
	public String group() default "";
}
