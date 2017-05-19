package org.com.cn.xu.util.ibatis.plugin;

import java.io.Serializable;

/**
 * 请求中ip地址，mac地址，计算机名称基础定义
 * @Title: ProtocalData.java
 * @Package com.framework.servlet.request
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class ProtocalData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ip;
	
	private String mac;
	
	private String macName;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMacName() {
		return macName;
	}
	public void setMacName(String macName) {
		this.macName = macName;
	}
	
}
