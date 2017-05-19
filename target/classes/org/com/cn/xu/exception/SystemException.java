package org.com.cn.xu.exception;

/**
 * 系统异常类，有框架丢出，如配置文件缺失，服务器无法启动，路径错误等。
 * @Title: SystemException.java
 * @Package com.framework.core.exception
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
//extends RpcException
public final class SystemException extends RuntimeException {
	private static final long serialVersionUID = 4013125033768022343L;
	
	private String errorCode;
	
	public SystemException(String msg,String errorCode) {
		super(msg);
		//super(RpcLangMsg.getMsg(msg));
		this.errorCode = errorCode;
	}

	/*public SystemException(String msg,String errorCode,Object...params) {
		//super(RpcLangMsg.getMsg(msg,params));
		super(msg);
		this.errorCode = errorCode;
	}

	public SystemException(String msg,String errorCode,Throwable ex){
		//super(RpcLangMsg.getMsg(msg), ex);
		super(msg);
		this.errorCode = errorCode;
	}*/

	public String getErrorCode() {
		return errorCode;
	}
	
	private static String getString(String msg){
		return  msg==null?"":msg+":";
	}
}

