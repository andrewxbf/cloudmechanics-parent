package org.com.cn.xu.util.ibatis.plugin.common;

import java.util.Date;

/**
 * 默认的日期获取方式，去本服务器日期时间
 * @Title: DefaultDateService.java
 * @Package com.framework.core.commons
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class DefaultDateService implements IDateService {
	/*
	 * 查看接口的注释描述
	 * @see com.framework.core.commons.IDateService#getCurrentDate()
	 */
	@Override
	public Date getCurrentDate() {
		return new Date();
	}

}
