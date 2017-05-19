package org.com.cn.xu.util.ibatis.plugin.validate;
/**
 * bean validator验证接口定义
 * @Title: IValidator.java
 * @Package com.framework.service.validate
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public interface IValidator {
	/**
	 * 验证整个bean对象
	 * @param obj 被验证的bean对象
	 * @param classes 验证分组
	 */
	public void validate(Object obj, Class<?>... classes);
	/**
	 * 验证bean对象中的某个属性
	 * @param target 被验证bean的class
	 * @param property 属性名
	 * @param obj 属性值
	 * @param classes 验证分组
	 */
	public void validate(Class<?> target, String property, Object obj, Class<?>... classes);
}
