package org.com.cn.xu.util.ibatis.plugin;
/**
 * 通过本地线程变量来临时存储分页sql的总记录数
 * @Title: PaginationRuntime.java
 * @Package com.framework.service.dao.pagination
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class PaginationRuntime {
	
	private static ThreadLocal<Integer> _totalRecords = new ThreadLocal<Integer>();
	
	public static Integer getTotalCount(){
		Integer counts =  _totalRecords.get();
		_totalRecords.remove();
		return counts;
	}
	
	public static void setTotalCount(Integer totalCount){
		_totalRecords.set(totalCount);
	}
}
