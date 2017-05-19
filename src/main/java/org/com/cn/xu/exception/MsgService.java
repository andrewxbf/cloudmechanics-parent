package org.com.cn.xu.exception;

import org.com.cn.xu.util.StringUtil;

import java.text.MessageFormat;

/**
 * 多语言工具类
 * @Title: MsgService.java
 * @Package com.framework.core.config
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class MsgService {
	
	private static String LANG_SUPPORT = "lang:";
	
	/**
	 * 根据语言类型获取对应的语句
	 * @param lang 语言类型en,zh_CN,us等
	 * @param key 语句文件Properties中的键，如：lang:com.framework.servlet.config.config.properties$dubbo.registry
	 * @param params 语句中的参数{0},{1}等
	 * @return 返回java字符串类型，对应语言的语句
	 */
	public static String getMsg(String lang,String key,Object...params){
		String msg = key;
		if(key.startsWith(LANG_SUPPORT)&&key.indexOf("$")>0){
			msg = getLocale(lang,key,params);
		}
		return msg;
	}
	/**
	 * 获取默认语言的语句，对应属性文件无语言后缀
	 * @param key 语句文件Properties中的键，如：lang:com.framework.servlet.config.config.properties$dubbo.registry
	 * @param params 语句中的参数{0},{1}等
	 * @return 返回java字符串类型，对应语言的语句
	 */
	public static String getMsg(String key,Object...params){
		return getMsg(null,key,params);
	}
	
	private static String getLocale(String lang,String key,Object... params){
		int index = key.lastIndexOf("$");
		String msgkey = key.substring(index+1);
		String msgfile = key.substring(LANG_SUPPORT.length(),index);
		if(StringUtil.isNotEmpty(lang)){
			int findex = msgfile.lastIndexOf(".");
			msgfile = msgfile.substring(0,findex)+"_"+lang+msgfile.substring(findex);
		}
		AppConfig config = new AppConfig(msgfile);
		String msg = config.getProperty(msgkey);
		if(msg == null){
			return "no message found for "+lang;
		}
		return MessageFormat.format(msg, params);
	}
	
	/*public static void main(String[] args){
		String key = "lang:com.framework.servlet.config.config.properties$dubbo.registry";
		System.out.println(MsgService.getMsg(null, key));
	}*/
}
