package org.com.cn.xu.exception;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/****
 * 配置文件缓存器
 * @author zhangqiang
 * 
 */
public class AppConfig {
	private static final Logger LOG = Logger.getLogger(AppConfig.class);
	
	/*
	 * 缓存属性配置文件
	 */
	private static Map<String,Properties> propsMap = new ConcurrentHashMap<String, Properties>();
	
	private static PathMatchingResourcePatternResolver pmrp = new PathMatchingResourcePatternResolver();
	/*
	 * 当前使用的属性文件
	 */
	private String currentFile;
	
	private void setCurrentFile(String currentFile) {
		this.currentFile = currentFile;
	}
	public AppConfig(String fileName){
		initial(fileName);
	}
	/***
	 * 初始化属性文件对象PropsConfig
	 * @param fileName 属性文件名
	 */
	private void initial(String fileName){
		if(fileName == null) {
			throw new SystemException("fileName cannot be null",ErrorCode.ERROR_CODE_FILENAME_NULL);
		}
		String cacheName = fileName;
		try{
			if(!propsMap.containsKey(fileName)){
				Properties properties = new Properties();
				File file = new File(fileName);
				InputStream is = null;
				if(file.exists() && file.isFile()){
					is = new FileInputStream(file);
				}else{
					int index = fileName.lastIndexOf(".");
					if(index<0){
						throw new SystemException(fileName+" is not properties file",ErrorCode.ERROR_CODE_FILETYPE_ERROR);
					}
					String suffix = fileName.substring(index);
					String prefix = fileName.substring(0, index).replace(".", "/");
					fileName = "classpath:"+prefix+suffix;
					LOG.debug(fileName);
					//Loggers.debug(fileName);
					Resource resource = pmrp.getResource(fileName);
					is = resource.getInputStream();
				}
				if(is == null) throw new SystemException(fileName + " cannot be found.",ErrorCode.ERROR_CODE_FILE_NOTFOUND);
				properties.load(is);
				propsMap.put(cacheName, properties);
				setCurrentFile(cacheName);
			}else{
				setCurrentFile(cacheName);
			}
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			//Loggers.error(ex);
			try {
				throw new SystemException(fileName+" cannot be loaded.",ErrorCode.ERROR_CODE_FILE_FAILED_LOADED);
			} catch (SystemException e) {
				LOG.error(e.getMessage());
			}
		}
	}
	/***
	 * 根据key值在当前属性文件中取值
	 * @param key 键值
	 * @return返回对应的value值
	 */
	public String getProperty(String key){
		String result = null;
		if(currentFile!=null && propsMap.containsKey(currentFile)){
			result = propsMap.get(currentFile).getProperty(key);
		}
		return result;
	}
	/***
	 * 获取当前配置文件的属性对象
	 * @returnProperties对象
	 */
	public Properties getProperties(){
		return propsMap.get(currentFile);
	}
	/***
	 * 获取文件fileName的属性对象（缓存中不存在则返回NULL）
	 * @param fileName 属性文件名
	 * @returnProperties对象
	 */
	public Properties getProperties(String fileName){
		return propsMap.get(fileName);
	}
	
	/*public static void main(String[] args){
//		AppConfig config = new AppConfig("com.framework.service.service.properties");
//		Properties props = config.getProperties();
//		System.out.println("111");
		
		System.out.println(Locale.US.toString());
	}*/
}
