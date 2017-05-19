package org.com.cn.xu.exception;


import org.apache.log4j.Logger;

/***
 * 数据库访问异常类
 * @author zhangqiang
 * @deprecated
 */
@Deprecated
public final class ServiceException extends RuntimeException  {
	private static final long serialVersionUID = 4013125033768022343L;
    private static Logger loggers = Logger.getLogger(ServiceException.class);

    /***
     * @author zhangqiang
     * @deprecated
     */
	@Deprecated
	public ServiceException(String msg) {
//		super(RpcLangMsg.getMsg(msg));
        super(msg);
    }

    /***
     * @author zhangqiang
     * @deprecated
     */
    @Deprecated
    @SuppressWarnings("unused")
	public ServiceException(String msg, Object... params) {
//		super(RpcLangMsg.getMsg(msg, params));
        super(msg);
        loggers.error(params);
    }

}

