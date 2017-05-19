package org.com.cn.xu.exception;
/***
 * 
 * @author zhangqiang
 * 4位errorcode系统预留
 *
 */
public interface ErrorCode {
	/**
	 * 配置文件为空
	 */
	String ERROR_CODE_FILENAME_NULL = "0000";
	/**
	 * 配置文件类型错误
	 */
	String ERROR_CODE_FILETYPE_ERROR = "0001";
	/**
	 * 文件未找到
	 */
	String ERROR_CODE_FILE_NOTFOUND = "0002";
	/**
	 * 文件加载失败
	 */
	String ERROR_CODE_FILE_FAILED_LOADED = "0003";
	/**
	 * 数据源为找到
	 */
	String ERROR_CODE_DATASOURCE_NOTFOUND= "0004";
	/**
	 * 数据库连接打开失败
	 */
	String ERROR_CODE_DBCONN_OPENFAILED= "0005";
	/**
	 * 数据库提交事务失败
	 */
	String ERROR_CODE_DBCONN_COMMIT_FAILED= "0006";
	/**
	 * 数据库回滚失败
	 */
	String ERROR_CODE_DBCONN_ROLLBACK_FAILED= "0007";
	/**
	 *未配置IBATIS操作对象
	 */
	String ERROR_CODE_SQLMAPCLIENT_NOTFOUND= "0008";
	/**
	 *SAP调用函数错误
	 */
	String ERROR_CODE_SAPFUNCTION_ERROR= "0009";
	/**
	 *定时任务格式配置错误
	 */
	String ERROR_CODE_TIMETASK_EXPRESSION_ERROR= "0010";
	/**
	 *SAP连接错误
	 */
	String ERROR_CODE_SAPCONN_ERROR= "0011";
	/**
	 *SAP数据源未找到
	 */
	String ERROR_CODE_SAPSOURCE_NOTFOUND= "0012";
	/**
	 *database读取数据错误
	 */
	String ERROR_CODE_DATABASE_QUERY_ERROR= "0013";
	/**
	 *database插入数据错误
	 */
	String ERROR_CODE_DATABASE_INSERT_ERROR= "0014";
	/**
	 *database更新数据错误
	 */
	String ERROR_CODE_DATABASE_UPDATE_ERROR= "0015";
	/**
	 *database删除数据错误
	 */
	String ERROR_CODE_DATABASE_DELETE_ERROR= "0016";
	/**
	 *database分页读取数据错误
	 */
	String ERROR_CODE_DATABASE_PAGEQUERY_ERROR= "0017";
	
	/**
	 *dao初始化错误
	 */
	String ERROR_CODE_DAO_INIT_ERROR= "0018";
	/**
	 *ws调用服务失败
	 */
	String ERROR_CODE_WSINVOKE_ERROR= "0019";
	/**
	 *jpa校验错误
	 */
	String ERROR_CODE_VALIDJPA_ERROR= "0020";
	/**
	 * 异步调用异常
	 */
	String ERROR_CODE_ASYNINVOKE_ERROR= "0021";
	/**
	 * 未找到定时任务
	 */
	String ERROR_CODE_TIMETASK_NOTFOUND= "0022";
	/**
	 * 定时任务启动失败
	 */
	String ERROR_CODE_TIMETASK_START_FAILED= "0023";
	/**
	 * 定时任务停止失败
	 */
	String ERROR_CODE_TIMETASK_STOP_FAILED= "0024";
	/**
	 * 服务授权错误
	 */
	String ERROR_CODE_SERVICE_AUTH_ERROR= "0025";
	/**
	 * 未找到服务
	 */
	String ERROR_CODE_SERVICE_NOTFOUND= "0026";
	/**
	 * 请求上下文初始化失败
	 */
	String ERROR_CODE_CONTEXT_INIT_ERROR= "0027";
	/**
	 * 会话过期或未登陆
	 */
	String ERROR_CODE_SESSION_ERROR= "0028";
	/**
	 * 未找到应用级参数
	 */
	String ERROR_CODE_APPLICATION_PARAM_ERROR= "0029";
	/**
	 * 参数解析失败
	 */
	String ERROR_CODE_REQUEST_PARSER_ERROR= "0030";
	/**
	 * 请求响应错误
	 */
	String ERROR_CODE_RESPONSE_PARSER_ERROR= "0031";
	/**
	 * 文件传输异常
	 */
	String ERROR_CODE_RESPONSE_FILE_ERROR= "0032";
	/**
	 * 响应器未找到
	 */
	String ERROR_CODE_RESPONSE_PARSER_NOTFOUND= "0033";
	/**
	 * 服务权限验证器丢失
	 */
	String ERROR_CODE_AUTHER_NOTFOUND= "0034";
	/**
	 *请求解析器未找到
	 */
	String ERROR_CODE_REQUEST_PARSER_NOTFOUND= "0035";
	/**
	 *服务执行器未找到
	 */
	String ERROR_CODE_EXECUTOR_NOTFOUND= "0036";
	/**
	 *服务调用异常
	 */
	String ERROR_CODE_SERVICE_INVOKED_ERROR= "0037";
	/**
	 *上传文件异常
	 */
	String ERROR_CODE_FILE_UPLOAD_ERROR= "0038";
	/**
	 *服务版本异常
	 */
	String ERROR_CODE_SERVICE_VERSION_ERROR= "0039";
	/**
	 *服务初始化异常
	 */
	String ERROR_CODE_SERVICE_INIT_ERROR= "0040";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 *其他异常
	 */
	String ERROR_CODE_OTHERS_ERROR= "0999";
}
