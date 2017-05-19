package org.com.cn.xu.util;

import java.util.List;

/**
 * <p>Title:JaonPage赋值Pagination</p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2016</p>
 * <p>Company: 杭州易云科技</p>
 * <p>Date:2016-03-23</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * @author Zhoujx
 * @version 1.0
 */

public class JsonPageUtils {
	
	/**
	 * 设置分页显示数据调用此方法
	 * @param jsonpage
	 * @param page
	 * @return
	 */
	public static <T> JsonPage<T> setPage(JsonPage<T> jsonpage,Pagination<T> page) {  
		jsonpage.setRows((List<T>) page.getItems());
		jsonpage.setPagesize(page.getPageSize());
		jsonpage.setTotal(page.getTotalRecords());
		jsonpage.setCurrentindex(page.getCurrentPageIndex());
		jsonpage.setPagecount(page.getPageCount());
	    return jsonpage;  
	} 
}
