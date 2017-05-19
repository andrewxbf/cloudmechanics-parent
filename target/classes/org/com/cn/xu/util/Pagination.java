package org.com.cn.xu.util;

import java.io.Serializable;
import java.util.List;

/***
 * 分页对象
 * @author zhangqiang
 *
 */
public class Pagination<T> implements Serializable {
	
	private static final long serialVersionUID = 30161819074846596L;
	/**
	 * 默认每页显示数
	 */
	public static final int PAGESIZE=25;
	/**
	 * 每页显示数
	 */
	private int pageSize = PAGESIZE;
	/**
	 * 总记录数
	 */
	private int totalRecords;
	/**
	 * 总页数
	 */
	private int pageCount;
	/*
	 * 当前页码
	 */
	private int currentPageIndex;
	/**
	 * 开始索引数
	 */
	private int offset;
	/**
	 * 显示项集合
	 */
	private List<?> items;
	
	/**
	 * 
	 * @param items 显示项集合
	 * @param totalRecords 总记录数
	 */
	public Pagination(List<?> items, int totalRecords) {
		setTotalRecords(totalRecords);
		setOffset(0);
		setItems(items);
	}

	/**
	 * 
	 * @param items 显示项集合
	 * @param totalRecords 总记录数
	 * @param offset 开始索引数
	 */
	public Pagination(List<T> items, int totalRecords, int offset) {
		setTotalRecords(totalRecords);
		setOffset(offset);
		setItems(items);
	}

	/**
	 * 
	 * @param items 显示项集合
	 * @param totalRecords 总记录数
	 * @param offset 开始索引数
	 * @param pageSize 每页显示数
	 */
	public Pagination(List<T> items, int totalRecords, int offset,
                      int pageSize) {
		setPageSize(pageSize);
		setTotalRecords(totalRecords);
		setOffset(offset);
		setItems(items);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 设置总记录数，总页数
	 * @param totalRecords 总记录数
	 */
	public void setTotalRecords(int totalRecords) {
		if (totalRecords > 0) {
			this.totalRecords = totalRecords;
			int count = totalRecords / pageSize;
			if (totalRecords % pageSize > 0)
				count++;
			this.pageCount = count;
		} else {
			this.totalRecords = 0;
		}
	}
	/**
	 * 获取当页记录开始索引
	 * @return
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * 设置显示页相应的开始索引数
	 * @param offset 开始索引数
	 */
	public void setOffset(int offset) {
		if (totalRecords <= 0){
			this.totalRecords = 0;
			this.offset = 0;
		}else if (offset >= totalRecords)
			this.offset = totalRecords - 1;
		else if (offset < 0)
			this.offset = 0;
		this.currentPageIndex = offset / pageSize + 1;
//		int currentPageIndex = offset / pageSize;
//		if(offset % pageSize !=0 ) currentPageIndex++;
//		this.currentPageIndex = currentPageIndex;
	}
	/**
	 * 获得下一页索引
	 * @return 下一页索引
	 */
	public int getNextIndex() {
		int nextIndex = getOffset() + pageSize;
		if (nextIndex >= totalRecords)
			return getOffset();
		else
			return nextIndex;
	}

	/**
	 * 获得上一页索引 
	 * @return 上一页索引
	 */
	public int getPreviousIndex() {
		int previousIndex = getOffset() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}
	
	/**
	 * 获得分页数据
	 * @return 分页集合
	 */
	public List<T> getItems() {
		return (List<T>) items;
	}
	/**
	 * 获取总页数
	 * @return总页数
	 */
	public int getPageCount() {
		return pageCount;
	}
	/**
	 * 获取当前页码
	 * @return当前页码
	 */
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	/**
	 * 获取当页数据集
	 * @param items
	 */
	public void setItems(List<?> items) {
		this.items = items;
	}
	/**
	 * 获取当前页记录数
	 * @return当前页记录数
	 */
	public int getCurrentRecords(){
		return items==null?0:items.size();
	}
//	public static void main(String[] args) {
//		PaginationSupport ps = new PaginationSupport(null, 90, 1, 10);
//		System.out.println("总记录数：" + ps.getTotalResult());
//		System.out.println("总共页数：" + ps.getIndexes().length);
//		System.out.println("开始索引：" + ps.getStartResult());
//		System.out.println("下一页索引：" + ps.getNextIndex());
//		System.out.println("上一页索引：" + ps.getPreviousIndex());
//		System.out.println("每页显示数：" + ps.getPageSize());
//		for (int i = 0; i < ps.getIndexes().length; i++) {
//			System.out.println("第" + (i + 1) + "页从" + ps.getIndexes()[i]
//					+ "记录开始显示");
//		}
//	}
	
}
