package org.com.cn.xu.util.dao;


import org.com.cn.xu.util.Pagination;

/*******************************************************************************
 * 数据库访问通用接口
 * 
 * @author zhangqiang
 * 
 */
public interface GeneralDAO {
	/**
	 * 查询单条数据库记录（无参数）
	 * 
	 * @sqlId配置文件中SQLID
	 * @return返回记录对象
	 */
	public <T> T queryObject(String sqlId);

	/**
	 * 查询单条数据库记录（有参数）
	 * 
	 * @desc sqlId 配置文件中SQLID
	 * @desc param 查询参数
	 * @return返回记录对象
	 */
	public <T> T queryObject(String sqlId, Object param);

	/**
	 * 查询多条数据库记录（无参数）
	 * 
	 * @desc sqlId 配置文件中SQLID
	 * @return返回记录对象
	 */
	public <T> T queryForList(String sqlId);

	/**
	 * 查询多条数据库记录（有参数）
	 * 
	 * @desc sqlId 配置文件中SQLID
	 * @desc param 查询参数
	 * @return返回记录对象
	 */
	public <T> T queryForList(String sqlId, Object param);
	
	
	/**
	 * 查询单条数据库记录（无参数）
	 * 
	 * @sqlId配置文件中SQLID
	 * @return返回记录对象
	 */
	public <T> T queryObject(String sqlId, Class resultClass);

	/**
	 * 查询单条数据库记录（有参数）
	 * 
	 * @desc sqlId 配置文件中SQLID
	 * @desc param 查询参数
	 * @return返回记录对象
	 */
	public <T> T queryObject(String sqlId, Class resultClass, Object param);

	/**
	 * 查询多条数据库记录（无参数）
	 * 
	 * @desc sqlId 配置文件中SQLID
	 * @return返回记录对象
	 */
	public <T> T queryForList(String sqlId, Class resultClass);

	/**
	 * 查询多条数据库记录（有参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 * @desc param查询参数
	 * @return返回记录对象
	 */
	public <T> T queryForList(String sqlId, Class resultClass, Object param);

	/**
	 * 插入单条数据库记录（无参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 */
	public int insertObject(String sqlId);

	/**
	 * 插入单条数据库记录（有参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 */
	public int insertObject(String sqlId, Object param);

	/**
	 * 删除数据库记录（无参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 * @return返回删除的记录数
	 */
	public int deleteObject(String sqlId);

	/**
	 * 删除数据库记录（有参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 * @return返回删除的记录数
	 */
	public int deleteObject(String sqlId, Object param);

	/**
	 * 更新数据库记录（无参数）
	 * 
	 * @desc sqlId配置文件中SQLID
	 * @return返回更新的记录数
	 */
	public int updateObject(String sqlId);

	/**
	 * 更新数据库记录（有参数）
	 * 
	 * @dsqlId配置文件中SQLID
	 * @desc更新参数
	 * @return返回更新的记录数
	 */
	public int updateObject(String sqlId, Object param);

	/**
	 * 分页查询（有参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex, int pageSize);

    /**
     * 分页查询（有参数可排序）
     *
     * @desc itemSqlId
     *            数据项sqlmap名称
     * @desc parameter
     *            查询条件
     * @desc currentPageIndex
     *            显示第几页数据
     * @desc pageSize
     *            每页显示记录数
     * @desc sort
     *            排序参数 "sort order" --"CREATED_TIME DESC"
     * @desc order
     *
     * @return分页对象
     */
    Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex, int pageSize, String sort, String order);

	/**
	 * 分页查询（无参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, int currentPageIndex, int pageSize);

	/**
	 * 分页查询（有参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex);

	/**
	 * 分页查询（无参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, int currentPageIndex);
	
	/**
	 * 分页查询（有参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Class resultClass, Object parameter, int currentPageIndex, int pageSize);

	/**
	 * 分页查询（无参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Class resultClass, int currentPageIndex, int pageSize);

	/**
	 * 分页查询（有参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Class resultClass, Object parameter, int currentPageIndex);

	/**
	 * 分页查询（无参数）
	 * 
	 * @desc itemSqlId
	 *            数据项sqlmap名称
	 * @desc countSqlId
	 *            总页数sqlmap名称
	 * @desc parameter
	 *            查询条件
	 * @desc currentPageIndex
	 *            显示第几页数据
	 * @desc pageSize
	 *            每页显示记录数
	 * @return分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, Class resultClass, int currentPageIndex);

}
