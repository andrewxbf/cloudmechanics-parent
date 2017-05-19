package org.com.cn.xu.util.ibatis.plugin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * json格式的分页数据定义
 * @Title: JsonPage.java
 * @Package com.framework.servlet.response
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class JsonPage<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total;
	private int pagecount;
	private int currentindex;
	private int pagesize;
	private List<T> rows;
	private List<T> footer = new ArrayList<T>();
	
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getCurrentindex() {
		return currentindex;
	}
	public void setCurrentindex(int currentindex) {
		this.currentindex = currentindex;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public List<T> getFooter() {
		return footer;
	}
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	public void addFooterRow(T row){
		if(footer != null){
			footer.add(row);
		}
	}
}
