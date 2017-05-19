package org.com.cn.xu.util.ibatis.plugin.util;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.com.cn.xu.exception.ErrorCode;
import org.com.cn.xu.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * webservice请求工具类
 * @Title: SoapUtils.java
 * @Package com.framework.service.utils
 * @Description:用于调用外部的webservice接口，基于xfire开发
 * @author maxwell
 * @version V1.0
 * @date
 */
public class SoapUtils {
	private static final Logger LOG = LoggerFactory.getLogger(SoapUtils.class);
	//15分钟超时
	private static final int TIME_OUT = 800000;
	/**
	 * 调用webService接口
	 * @param url wsdl地址
	 * @param method webservice名字
	 * @param param 请求参数
	 * @return 调用webservice返回对象
	 */
	public static Object[] invokeWebService(String url,String method,Object[] param){
		try{
			if(param==null) param = new Object[0];
			URL _url = new URL(url);  
			HttpURLConnection httpConnection = (HttpURLConnection)_url.openConnection();  
			httpConnection.setReadTimeout(TIME_OUT);//设置http连接的读超时,单位是毫秒  
			httpConnection.connect();  
			Client client = new Client(httpConnection.getInputStream(), null);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, Integer.toString(TIME_OUT));//设置发送的超时限制,单位是毫秒;  
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");  
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");  

			client.setTimeout(TIME_OUT);
			return client.invoke(method, param);
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
			throw new SystemException("call WebService failed.", ErrorCode.ERROR_CODE_WSINVOKE_ERROR);
		}
	}
	
	private static Object createWebServiceClient(String url, Class<?> iface) {
		Object result = null;
		Service ss = new ObjectServiceFactory().create(iface, null, url, null);
		XFireProxyFactory factory = new XFireProxyFactory();
		try {
			result = factory.create(ss, url);
			Client client =Client.getInstance(result);  
			client.setTimeout(360000);  
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, Integer.toString(TIME_OUT));  
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");  
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true"); 
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new SystemException("call WebService failed.",ErrorCode.ERROR_CODE_WSINVOKE_ERROR);
		}
	}
	/**
	 * 创建webservice本地引用对象
	 * @param url wsdl地址
	 * @param iface 通过wsdl生成的引用接口
	 * @return
	 */
	public static Object createWSClient(String url, Class<?> iface) {
		return createWebServiceClient(url,iface);
	}
	
	
	
/*	public static void main(String[] args){
		//("MongateSendSubmit", new Object[] { auth.getUSER_ID(), auth.getPASSWORD(), phones, smeMsg, count, subPort,msgId.toString()});
//		Object[] objs = SoapUtils.invokeWebService("http://61.145.229.29:7903/MWGate/wmgw.asmx", "MongateSendSubmit", new Object[] { "J22198", "250063", "15606520807", "测试", 1, "",""});
//		System.out.println(objs.length);
		
		String ss = "1D0DEE91BE87836F3EA9DA2A60D6A6DA6E96F56E07C5D40498E6A9735799E4A15504D8C1553A69B972FEF84B4FD86B0CF891A622C80737DF8D6E977DEB00456C";
		Object[] objs = SoapUtils.invokeWebService("http://10.86.87.42/GeelyAuthService/LoginAuthentication.asmx?wsdl", "MD5Decrypt", new Object[] {ss});
		System.out.println(objs.length);
	}*/
	
}
