package org.com.cn.xu.util.ibatis.plugin.service;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 参数分组工具类
 * @Title: ParamUnitGroupMgr.java
 * @Package com.framework.servlet.paramunit
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class ParamUnitGroupMgr {
	/**
	 * 判断是否为 可接收/返回的参数组
	 * @param basis
	 * @param value
	 * @return
	 */
	public static boolean isNeededGroup(String basis,String value){
		if(StringUtils.isEmpty(basis)){
			return true;
		}
		if(StringUtils.isEmpty(value)){
			return false;
		}
		String[] groups1 = basis.split(",");
		String[] groups2 = value.split(",");
		for(String g2:groups2){
			if("@ALL".equals(g2)) return true;
			for(String g1:groups1){
				if(g1.equals(g2)){
					return true;
				}
			}
		}
		return false;
	}
}
