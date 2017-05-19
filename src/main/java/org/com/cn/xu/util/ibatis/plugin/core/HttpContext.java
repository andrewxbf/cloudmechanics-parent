package org.com.cn.xu.util.ibatis.plugin.core;
/**
 * http请求上下文
 * @Title: HttpContext.java
 * @Package com.framework.servlet.core
 * @Description:封装request，response，提供各种请求中参数的解析，远程服务对象的获取，请求响应的 组装等工具函数。
 * @author maxwell
 * @version V1.0
 * @date
 */
public class HttpContext {/*
	*//**
	 * 保留参数名
	 *//*
	*//**
	 * 请求类型
	 *//*
	public static final String _request_type = "_request_type";
	*//**
	 * 响应类型
	 *//*
	public static final String _response_type="_response_type";
	*//**
	 * 接口全名
	 *//*
	public static final String _interface="_interface";
	*//**
	 * 方法名
	 *//*
	public static final String _method="_method";
	*//**
	 * 服务版本
	 *//*
	public static final String _version="_version";
	*//**
	 * 服务分组
	 *//*
	public static final String _group="_group";
	*//**
	 * 服务ID
	 *//*
	public static final String _serviceId="_serviceid";
	*//**
	 * 服务类型
	 *//*
	public static final String _service_type = "_service_type";
	*//**
	 * 服务密码
	 *//*
	public static final String _service_password="_service_password";
	*//**
	 * 会话ID
	 *//*
	public static final String _sessionid="_sessionid";
	*//**
	 * 语言支持
	 *//*
	public static final String _lang="lang";
	
	private static final String MSG_FILE = "lang:com.framework.servlet.server.Servlets.properties$";
	
	private static final ThreadLocal<HttpContext> LOCAL = new ThreadLocal<HttpContext>(){
		@Override
		protected HttpContext initialValue() {
			return new HttpContext();
		}
	};
	
	private HttpContext(){
	}
	
	*//**
	 * get context.
	 * 
	 * @return context
	 *//*
	public static HttpContext getContext() {
	    HttpContext context = LOCAL.get();
	    if(context.initial){
	    	return context;
	    }
	    throw new SystemException("HttpContext is not initialized",ErrorCode.ERROR_CODE_CONTEXT_INIT_ERROR);
	}
	
	*//**
	 * remove context.
	 * 
	 * @see com.alibaba.dubbo.rpc.filter.ContextFilter
	 *//*
	public static void removeContext() {
	    LOCAL.remove();
	}
	
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String,String> cookie;
	private boolean initial;
	
//	public HttpContext(HttpServletRequest request,HttpServletResponse response){
//		this.request = request;
//		this.response = response;
//	}
	
	private String getParam(String name){
		String value = request.getParameter(name);
		if(StringUtils.isEmpty(value)) value = (String) request.getAttribute(name);
		return value;
	}
	
	private String getCookie(String name){
		if(cookie == null){
			cookie = new HashMap<String,String>();
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length>0){
				for(Cookie item:cookies){
					String cvalue = item.getValue();
					String cname = item.getName();
					cookie.put(cname, cvalue);
				}
			}
		}
		return cookie.get(name);
	}
	*//**
	 * 根据名称及生命周期获取参数值
	 * @param name 参数名
	 * @param scope 生命周期，包括application,session,request。
	 * @return 返回参数值字符串
	 *//*
	public String getParam(String name,Scope scope){
		if(scope == null) return getParam(name);
		Object tar = null;
		switch(scope){
		case request:
			return getParam(name);
		case session:
			tar = request.getSession().getAttribute(name);
			if(tar != null && tar instanceof String){
				return (String)tar;
			}
			throw new SystemException(MSG_FILE+"009",ErrorCode.ERROR_CODE_SESSION_ERROR);
		case application:
			tar = request.getSession().getServletContext().getAttribute(name);
			if(tar != null && tar instanceof String){
				return (String)tar;
			}
			throw new SystemException(MSG_FILE+"010",ErrorCode.ERROR_CODE_APPLICATION_PARAM_ERROR);
		case cookie:
			return getCookie(name);
		}
		return null;
	}
	*//**
	 * 获取Session
	 * @return 返回被格式化为json字符串的session信息
	 *//*
	public String getSession(){
		String sessionId = getParam(_sessionid);
		if(StringUtils.isEmpty(sessionId)){
			sessionId = getParam(_sessionid.toUpperCase());
		}
		return getParam(sessionId,Scope.session);
	}
	*//**
	 * 获取语言包
	 * @return
	 *//*
	public String getLanguage(){
		String lang = request.getParameter(_lang);
		if(StringUtils.isEmpty(lang)){
			Object obl = request.getAttribute(_lang);
			if(obl != null && obl instanceof String){
				lang = (String)obl;
			}
		}
		if(StringUtils.isEmpty(lang)){
			Object obl = request.getSession().getAttribute(_lang);
			if(obl != null && obl instanceof String){
				lang = (String)obl;
			}
		}
		if("default".equals(lang)){
			lang = null;
		}
		return lang;
	}
	*//**
	 * 销毁session
	 *//*
	public void loginout(){
		this.request.getSession().invalidate();
	}
	*//**
	 * 根据名称获取请求头信息
	 * @param name 请求头名称
	 * @return 请求头名称对应的值
	 *//*
	public String getHeader(String name){
		return request.getHeader(name);
	}
	*//**
	 * 设置响应类型
	 * @param type 响应类型
	 *//*
	public void setContentType(String type){
		response.setContentType(type);
	}
	*//**
	 * 添加响应头信息
	 * @param name 响应头键
	 * @param value 响应值
	 *//*
	public void addHeader(String name,String value){
		response.addHeader(name, value);
	}
	*//**
	 * 获取http请求方的IP地址，MAC地址等信息
	 * @return java对象
	 *//*
	public ProtocalData getProtocalData(){
		ProtocalData pd = new ProtocalData();
		pd.setIp(getCientIp());
		pd.setMacName(getClientName());
		return pd;
	}
	*//**
	 * 获取请求中的数组
	 * @param name 参数名
	 * @return 字符串数组
	 *//*
	public String[] getListParam(String name){
		return request.getParameterValues(name);
	}
	*//**
	 * 解析请求中的服务密码
	 * @return 服务密码
	 *//*
	public String getServicePassword(){
		String servicePassword = getParam(_service_password);
		if(StringUtils.isEmpty(servicePassword)){
			servicePassword = getParam(_service_password.toUpperCase());
		}
		return servicePassword;
	}
	*//**
	 * 获取服务接口名称
	 * @return
	 *//*
	public String getInterface(){
		String interfacestr = getParam(_interface);
		if(StringUtils.isEmpty(interfacestr)){
			interfacestr = getParam(_interface.toUpperCase());
		}
		return interfacestr;
	}
	*//**
	 * 获取服务方法
	 * @return
	 *//*
	public String getMethod(){
		String method = getParam(_method);
		if(StringUtils.isEmpty(method)){
			method = getParam(_method.toUpperCase());
		}
		return method;
	}
	*//**
	 * 获取服务版本
	 * @return
	 *//*
	public String getVersion(){
		String version = getParam(_version);
		if(StringUtils.isEmpty(version)){
			version = getParam(_version.toUpperCase());
		}
		return version;
	}
	*//**
	 * 获取服务分组
	 * @return
	 *//*
	public String getGroup(){
		String group = getParam(_group);
		if(StringUtils.isEmpty(group)){
			group = getParam(_group.toUpperCase());
		}
		return group;
	}
	*//**
	 * 获取服务类型
	 * @return
	 *//*
	public String getServiceType(){
		String serviceType = getParam(_service_type);
		if(StringUtils.isEmpty(serviceType)){
			serviceType = getParam(_service_type.toUpperCase());
		}
		return serviceType;
	}
	*//**
	 * 获取服务ID
	 * @return
	 *//*
	public Long getServiceId(){
		try{
			String strid = getParam(_serviceId);
			if(StringUtils.isEmpty(strid)){
				strid = getParam(_serviceId.toUpperCase());
			}
			if(strid != null){
				return Long.parseLong(strid);
			}
		}catch(Exception ex){
		}
		return null;
	}
	*//**
	 * 获取请求参数Map
	 * @return
	 *//*
	public Map<String,String> getRequestMap(){
		return request.getParameterMap();
	}
	*//**
	 * 将数据存储在不同的生命周期中
	 * @param scope 生命周期，包括application,session,cookie,none.当为none时会销毁session，默认为request
	 * @param name
	 * @param value
	 *//*
	public void saveData(Scope scope,String name,Object value){
		if(scope != null){
			switch(scope){
			case session:
				request.getSession().setAttribute(name, Utils.toJsonString(name, value));
				break;
			case application:
				request.getSession().getServletContext().setAttribute(name, Utils.toJsonString(name, value));
				break;
			case cookie:
				Cookie cookie = new Cookie(name,Utils.toJsonString(name, value));
				cookie.setMaxAge(24*60*60);
				cookie.setPath("/");
				response.addCookie(cookie);
				break;
			case none:
				request.getSession().removeAttribute(name);
				request.getSession().getServletContext().removeAttribute(name);
				break;
			}
		}
	}
	*//**
	 * 获取请求解析器
	 * @return
	 *//*
	public IRequestParse getRequestParse(){
		String requestType = getParam(_request_type);
		if(StringUtils.isEmpty(requestType)){
			requestType = getParam(_request_type.toUpperCase());
		}
		return FApplicationContext.getRequestParseFactory().findRequestParse(requestType);
	}
	*//**
	 * 获取请求响应器
	 * @return
	 *//*
	public IResponseParse getResponseParse(){
		String responseType = getParam(_response_type);
		if(StringUtils.isEmpty(responseType)){
			responseType = getParam(_response_type.toUpperCase());
		}
		return FApplicationContext.getResponseParseFactory().findResponseParse(responseType);
	}
	*//**
	 * 获取服务定义
	 * @return
	 *//*
	public ServiceDefinition getServiceDefiniton(){
		Long serviceId = getServiceId();
		ServiceDefinition sd = null;
		if(serviceId != null){
			sd = FApplicationContext.getServiceDefinitionFactory().traceService(getServiceId(), getMethod(), getVersion(), getGroup());
		}
		if(sd == null){
			sd = FApplicationContext.getServiceDefinitionFactory().traceService(getInterface(), getMethod(), getVersion(), getGroup());
		}
		return sd;
	}
	*//**
	 * 获取服务执行器
	 * @return
	 *//*
	public IServiceExecutor getServiceExecutor(){
		return FApplicationContext.getServiceExecutor();
	}
	*//**
	 * 获取密码验证器
	 * @return
	 *//*
	public IAuthorizingRealm getAuthorizingRealm(){
		return FApplicationContext.getAuthorizingRealm();
	}
	
	*//***
	 * 获取客户端IP地址
	 * @return
	 *//*
	public String getCientIp(){
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("proxy-client-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("wl-proxy-client-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_x_forwarded_for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	*//**
	 * 获取计算机名称
	 * @return
	 *//*
	public String getClientName(){
		StringBuffer sb = new StringBuffer();
		String host = request.getRemoteHost();
		String user = request.getRemoteUser();
		int port = request.getRemotePort();
		sb.append(host==null?"":host).append(":").append(port).append("->").append(user==null?"":user);
		return sb.toString();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	*//**
	 * 初始化上下文
	 * @param request
	 * @param response
	 *//*
	public static void initalContext(HttpServletRequest request,HttpServletResponse response) {
		HttpContext context = LOCAL.get();
		context.request = request;
		context.response = response;
		RpcContext.getContext().setAttachment(_lang, context.getLanguage());
		context.initial = true;
	}

	public void setResponseType(String type){
		getRequest().setAttribute(_response_type, type);
	}
	
	public void response(String content){
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (Exception e) {
			Loggers.error(e);
		}
	}
*/}
