package org.com.cn.xu.exception;


import org.apache.log4j.Logger;

/**
 * 业务异常类，由业务逻辑丢出业务异常
 * @Title: BizException.java
 * @Package com.framework.core.exception
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
//RpcException
public final class BizException extends RuntimeException  {
	private static final long serialVersionUID = 4013125033768022343L;
	private static Logger loggers = Logger.getLogger(BizException.class);

	private String errorCode;
	
	public BizException(String msg,String errorCode) {
		//super(RpcLangMsg.getMsg(msg));
        super(msg);
        this.errorCode = errorCode;
	}
	
	@SuppressWarnings("unused")
	public BizException(String msg,String errorCode,Object...params) {
        //super(RpcLangMsg.getMsg(msg));
        super(msg);
		this.errorCode = errorCode;
		loggers.error(params);
	}

	@SuppressWarnings("unused")
	public BizException(String msg,String errorCode,Throwable ex){
        //super(RpcLangMsg.getMsg(msg));
        super(msg);
		this.errorCode = errorCode;
		loggers.error(ex);
	}

	public String getErrorCode() {
		return errorCode==null?"":errorCode;
	}
	
	@SuppressWarnings("unused")
	private static String getString(String msg){
		return  msg==null?"":msg+":";
	}
	
}

