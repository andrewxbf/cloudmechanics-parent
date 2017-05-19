package org.com.cn.xu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.exception.SystemException;
import org.com.cn.xu.exception.ServiceException;
import org.com.cn.xu.util.Response;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionInterceptor implements HandlerInterceptor {
	
	private static Logger LOG = Logger.getLogger(ExceptionInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		if(arg3 != null){
			Throwable cause = arg3;//ExceptionUtils.getCause(arg3);
			String msg = null;
			if(cause == null || !(cause instanceof ServiceException) && !(cause instanceof BizException) && !(cause instanceof SystemException)){
				msg="系统出现异常,请联系系统管理员";
				LOG.error(arg3.getMessage());
			}else{
				msg = cause.getMessage();
			}
			write(arg1, msg);
		}
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		return true;
	}   
	
	private void write(HttpServletResponse response, String msg) throws IOException{
		Response res = new Response();
		res.setStatus(0);
		res.setMessagecode(msg);
		
		PrintWriter out = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html");  
			out = response.getWriter(); 
			out.print(mapper.writeValueAsString(res)); 
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
}