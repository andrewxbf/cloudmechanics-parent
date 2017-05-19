package org.com.cn.xu.util.ibatis.plugin.common;

import java.util.Date;

/**
 * 日期获取接口,用于所用服务器获取统一的日期，提供本地服务器日期和Ntp服务器日期2中实现方式
 * @Title: IDateService.java
 * @Package com.framework.core.commons
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public interface IDateService {
	/**
	 * 获取系统日期时间
	 * @return java日期类型
	 */
	public Date getCurrentDate();
}
