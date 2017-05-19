package org.com.cn.xu.util.ibatis.plugin.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.sap.mw.jco.JCO;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import org.com.cn.xu.util.ibatis.plugin.json.JsonDateMorpher;
import org.com.cn.xu.util.ibatis.plugin.json.JsonDateValueProcessor;
import org.com.cn.xu.util.ibatis.plugin.json.JsonValidator;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 常用工具类包
 * @Title: Utils.java
 * @Package com.framework.servlet.util
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class Utils {
	
	static JsonConfig jsonConfig = new JsonConfig();
	private static JsonValidator jsonValidator = new JsonValidator();
	private static JsonValueProcessor defaultNull = new JsonValueProcessor() {

		@Override
		public Object processArrayValue(Object arg0, JsonConfig arg1) {
			return arg0;
		}

		@Override
		public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
			if(arg1 == null){
				return "";
			}
			return arg1;
		}
	};
	/**
	 * 初始化json转化
	 */
	static{
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class, defaultNull);
		jsonConfig.registerJsonValueProcessor(Double.class, defaultNull);
		jsonConfig.registerJsonValueProcessor(Boolean.class, defaultNull);
		JSONUtils.getMorpherRegistry().registerMorpher(new JsonDateMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyyMMdd HH:mm:ss","yyyyMMdd"})));
	}
	/**
	 * 判断是否为基本类型
	 * @param cls
	 * @return
	 */
	public static boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || cls == Boolean.class || cls == Byte.class || cls == Character.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Date.class || cls == Class.class || cls.equals(BigDecimal.class) || cls.equals(BigInteger.class);
	}
	/**
	 * 判断是否为JCO.Table
	 * @param cls
	 * @return
	 */
	public static boolean isJCOTable(Class<?> cls){
		return cls.equals(JCO.Table.class);
	}
	/**
	 * 判断是否为输入输出流
	 * @param cls
	 * @return
	 */
	public static boolean isStream(Class cls){
		return InputStream.class.isAnnotationPresent(cls) || OutputStream.class.isAnnotationPresent(cls) || InputStream.class.equals(cls) || OutputStream.class.equals(cls);
	}
	/**
	 * 判断是否为输入流
	 * @param cls
	 * @return
	 */
	public static boolean isInputStream(Class cls){
		return InputStream.class.isAnnotationPresent(cls) ||InputStream.class.equals(cls);
	}
	/**
	 * 判断是否为输出流
	 * @param cls
	 * @return
	 */
	public static boolean isOutputStream(Class cls){
		return OutputStream.class.isAnnotationPresent(cls) || OutputStream.class.equals(cls);
	}
	/**
	 * 判断是否为json格式字符串
	 * @param json
	 * @return
	 */
	public static boolean isJson(String json){
		if(json == null) {
			return false;
		}
		return jsonValidator.validate(json);
	}
	/**
	 * 将java对象转化为json字符串
	 * @param name
	 * @param obj
	 * @return
	 */
	public static String toJsonString(String name,Object obj) {
		if(StringUtils.isNotEmpty(name) && obj != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(name, obj);
			JSONObject jsonObj = JSONObject.fromObject(map,jsonConfig);
			return jsonObj.toString();
		}
		return null;
	}
	/**
	 * 将Map对象转化为json字符串
	 * @param params
	 * @return
	 */
	public static String toJsonString(Map<String,Object> params){
		if(params != null){
			JSONObject jsonObj = JSONObject.fromObject(params,jsonConfig);
			return jsonObj.toString();
		}
		return "{}";
	}
	/**
	 * 将json字符串转化为java对象
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	public static Object toBean(String jsonString,Class beanClass){
		if(StringUtils.isEmpty(jsonString)) {
			return null;
		}
		if(jsonString.startsWith("{")){
			return JSONObject.toBean(JSONObject.fromObject(jsonString, jsonConfig), beanClass);
		}else{
			JSONArray array = JSONArray.fromObject(jsonString, jsonConfig);
			return JSONArray.toList(array, beanClass);
		}
	}
	/**
	 * 将json字符串转化为java对象
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	public static Object toBean(String jsonString,Class beanClass,Map<String,Class> classMap){
		if(StringUtils.isEmpty(jsonString)) {
			return null;
		}
		if(jsonString.startsWith("{")){
			return JSONObject.toBean(JSONObject.fromObject(jsonString, jsonConfig), beanClass, classMap);
		}else{
			JSONArray array = JSONArray.fromObject(jsonString, jsonConfig);
			return JSONArray.toList(array, beanClass, classMap);
		}
	}
	/**
	 * 将java对象转化为json字符串
	 * @param obj
	 * @return
	 */
	public static String toBeanString(Object obj){
		if(obj==null) {
			return "{}";
		}
		if(obj instanceof Collection){
			return JSONArray.fromObject(obj, jsonConfig).toString();
		}else{
			return JSONObject.fromObject(obj, jsonConfig).toString();
		}
	}
	/**
	 * 将JSON字符串转化为Map对象
	 * @param jsonString
	 * @param classMap
	 * @return
	 */
	public static Map<String,Object> parseJsonString(String jsonString,Map<String,Class> classMap){
		if(StringUtils.isEmpty(jsonString)) {
			return null;
		}
		if(!jsonString.startsWith("{")) jsonString = "{"+jsonString;
        if(!jsonString.endsWith("}")) jsonString = jsonString+"}";
        JSONObject responsejson = JSONObject.fromObject(jsonString,jsonConfig);
        return (Map<String,Object>)JSONObject.toBean(responsejson, Map.class,classMap);
	}
	
	public static void main(String[] args){
//		List<String> lt = new ArrayList<String>();
//		lt.add("cccc");
//		lt.add("eeee");
//		String js = toJsonString("session", lt);
//		System.out.println(js);
		
		/*List<User> list = new ArrayList<User>();
		User u1 = new User();
		u1.setUSER_NAME("xxxx");
		u1.setUSER_CODE("ddddd");
		Date date = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse("20120101");
		}catch(Exception ex){
		}
		u1.setLAST_LOGIN_TIME(date);
		
		User u2 = new User();
		u2.setUSER_NAME("xxxx2");
		u2.setUSER_CODE("ddddd2");
		u2.setLAST_LOGIN_TIME(date);
		
		list.add(u1);
		list.add(u2);
		System.out.println(toBeanString(u2));
		System.out.println(toBeanString(list));
		
		String s1 = "{\"COMPANY_ID\":0,\"CREATED_BY\":0,\"CREATED_TIME\":\"\",\"DUTY_ID\":0,\"EMP_ID\":0,\"FORBIDDEN_TIME\":\"\",\"IS_INACTIVE\":0,\"IS_ONLINE\":0,\"LAST_LOGIN_TIME\":\"2012-01-01 00:00:00\",\"LOGIN_TIMES\":0,\"ORG_ID\":0,\"PASSWORD\":\"\",\"PWD_UPDATED_DATE\":\"\",\"RECORD_ID\":0,\"REMARK\":\"\",\"UPDATED_BY\":0,\"UPDATED_TIME\":\"\",\"UPDATE_STATUS\":\"\",\"USER_CATEGORY\":0,\"USER_CODE\":\"ddddd2\",\"USER_ID\":0,\"USER_NAME\":\"xxxx2\",\"VERSION\":0}";
		String s2 = "[{\"COMPANY_ID\":0,\"CREATED_BY\":0,\"CREATED_TIME\":\"\",\"DUTY_ID\":0,\"EMP_ID\":0,\"FORBIDDEN_TIME\":\"\",\"IS_INACTIVE\":0,\"IS_ONLINE\":0,\"LAST_LOGIN_TIME\":\"2012-01-01 00:00:00\",\"LOGIN_TIMES\":0,\"ORG_ID\":0,\"PASSWORD\":\"\",\"PWD_UPDATED_DATE\":\"\",\"RECORD_ID\":0,\"REMARK\":\"\",\"UPDATED_BY\":0,\"UPDATED_TIME\":\"\",\"UPDATE_STATUS\":\"\",\"USER_CATEGORY\":0,\"USER_CODE\":\"ddddd\",\"USER_ID\":0,\"USER_NAME\":\"xxxx\",\"VERSION\":0},{\"COMPANY_ID\":0,\"CREATED_BY\":0,\"CREATED_TIME\":\"\",\"DUTY_ID\":0,\"EMP_ID\":0,\"FORBIDDEN_TIME\":\"\",\"IS_INACTIVE\":0,\"IS_ONLINE\":0,\"LAST_LOGIN_TIME\":\"2012-01-01 00:00:00\",\"LOGIN_TIMES\":0,\"ORG_ID\":0,\"PASSWORD\":\"\",\"PWD_UPDATED_DATE\":\"\",\"RECORD_ID\":0,\"REMARK\":\"\",\"UPDATED_BY\":0,\"UPDATED_TIME\":\"\",\"UPDATE_STATUS\":\"\",\"USER_CATEGORY\":0,\"USER_CODE\":\"ddddd2\",\"USER_ID\":0,\"USER_NAME\":\"xxxx2\",\"VERSION\":0}]";
		
		Object so1 = toBean(s1, User.class);
		Object so2 = toBean(s2, User.class);
		
		System.out.println("ccc");*/
		Integer i = 1;
		int a = 2;
//		System.out.println(Utils.isPrimitive(Integer.class));
//		System.out.println(Utils.toBeanString(i));
		
//		String jsonString = toJsonString("session", list);
//		System.out.println(jsonString);
//		
//		Map<String,Class> classMaps = new HashMap<String,Class>();
//		classMaps.put("session", User.class);
//		
//		list = (List<User>) parseJsonString(jsonString,classMaps).get("session");
//		
//		
//		Map<String,Class> classMap = new HashMap<String,Class>();
//		classMap.put("session", String.class);
//		
//		lt = (List<String>) parseJsonString(js,classMap).get("session");
//		System.out.println("ccc");
		
		
	}
	
}
