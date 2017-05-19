package org.com.cn.xu.exception;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * 根据远程调用方的语言设置从远程请求上下文中获取对应的语言包
 * @Title: RpcLangMsg.java
 * @Package com.framework.service.lang
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class RpcLangMsg {
	
	private static final String LANG = "lang";
	
	public static String getMsg(String key,Object...params){
		String lang = RpcContext.getContext().getAttachment(LANG);
		return MsgService.getMsg(lang, key, params);
	}
	
}
