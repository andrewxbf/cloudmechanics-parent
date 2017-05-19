package org.com.cn.xu.util.ibatis.plugin;


//extends GeneralDAO
public class IbatisGeneralDAOImpl {/*

	private SqlMapClientTemplate sqlMapClientTemplate;

	
	private SqlMapClientTemplate obtainSqlMapClientTemplate(){
		return sqlMapClientTemplate;
	}

    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
        this.sqlMapClientTemplate = sqlMapClientTemplate;
    }

    
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryObject(java.lang.String, java.lang.Object)
	 
	@Override
	public <T>T queryObject(String sqlId, Object param) {
		return queryObject(sqlId,null,param);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryObject(java.lang.String, java.lang.Class, java.lang.Object)
	 
	@Override
	public <T>T queryObject(String sqlId,Class resultClass, Object param) {
		try {
			ResultMap.setLocalClass(resultClass);
			return (T)obtainSqlMapClientTemplate().queryForObject(sqlId, param);
		} catch (Exception e) {
			throw new SystemException("query data error。", ErrorCode.ERROR_CODE_DATABASE_QUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForList(java.lang.String, java.lang.Object)
	 
	@Override
	public <T>T queryForList(String sqlId, Object param) {
		return queryForList(sqlId,null,param);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForList(java.lang.String, java.lang.Class, java.lang.Object)
	 
	@Override
	public <T>T queryForList(String sqlId, Class resultClass, Object param) {
		try {
			ResultMap.setLocalClass(resultClass);
			return (T)obtainSqlMapClientTemplate().queryForList(sqlId, param);
		} catch (Exception e) {
			throw new SystemException("query data error。",ErrorCode.ERROR_CODE_DATABASE_QUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForList(java.lang.String)
	 
	@Override
	public <T>T queryForList(String sqlId) {
		return queryForList(sqlId,null);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForList(java.lang.String, java.lang.Class)
	 
	@Override
	public <T>T queryForList(String sqlId,Class resultClass) {
		try {
			ResultMap.setLocalClass(resultClass);
			return (T)obtainSqlMapClientTemplate().queryForList(sqlId);
		} catch (Exception e) {
			throw new SystemException("query data error。",ErrorCode.ERROR_CODE_DATABASE_QUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryObject(java.lang.String)
	 
	@Override
	public <T>T queryObject(String sqlId) {
		return queryObject(sqlId, null);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryObject(java.lang.String, java.lang.Class)
	 
	@Override
	public <T>T queryObject(String sqlId,Class resultClass) {
		try {
			ResultMap.setLocalClass(resultClass);
			return (T)obtainSqlMapClientTemplate().queryForObject(sqlId);
		} catch (Exception e) {
			throw new SystemException("query data error。",ErrorCode.ERROR_CODE_DATABASE_QUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#insertObject(java.lang.String)
	 
	@Override
	public Object insertObject(String sqlId) {
		try {
			return obtainSqlMapClientTemplate().insert(sqlId);
		} catch (Exception e) {
			throw new SystemException("insert data error。",ErrorCode.ERROR_CODE_DATABASE_INSERT_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#insertObject(java.lang.String, java.lang.Object)
	 
	@Override
	public Object insertObject(String sqlId, Object param) {
		try {
			return obtainSqlMapClientTemplate().insert(sqlId, param);
		} catch (Exception e) {
			throw new SystemException("insert data error。",ErrorCode.ERROR_CODE_DATABASE_INSERT_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#deleteObject(java.lang.String)
	 
	@Override
	public int deleteObject(String sqlId) {
		try {
			return obtainSqlMapClientTemplate().delete(sqlId);
		} catch (Exception e) {
			throw new SystemException("delete data error。",ErrorCode.ERROR_CODE_DATABASE_DELETE_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#deleteObject(java.lang.String, java.lang.Object)
	 
	@Override
	public int deleteObject(String sqlId, Object param) {
		try {
			return obtainSqlMapClientTemplate().delete(sqlId, param);
		} catch (Exception e) {
			throw new SystemException("delete data error。",ErrorCode.ERROR_CODE_DATABASE_DELETE_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#updateObject(java.lang.String)
	 
	@Override
	public int updateObject(String sqlId) {
		try {
			return obtainSqlMapClientTemplate().update(sqlId);
		} catch (Exception e) {
			throw new SystemException("update data error。",ErrorCode.ERROR_CODE_DATABASE_UPDATE_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#updateObject(java.lang.String, java.lang.Object)
	 
	@Override
	public int updateObject(String sqlId, Object param) {
		try {
			return obtainSqlMapClientTemplate().update(sqlId, param);
		} catch (Exception e) {
			throw new SystemException("update data error。",ErrorCode.ERROR_CODE_DATABASE_UPDATE_ERROR,e);
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Object, int, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex, int pageSize) {
		return queryForPaginatedList(itemSqlId,null,parameter,currentPageIndex,pageSize);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Class, java.lang.Object, int, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId,Class resultClass, Object parameter, int currentPageIndex, int pageSize) {
		try {
			ResultMap.setLocalClass(resultClass);
			if (currentPageIndex <= 0)
				currentPageIndex = 1;
			Pagination result = null;
			int offset = (currentPageIndex - 1) * pageSize;// 偏移值
			List<?> listItem = obtainSqlMapClientTemplate().queryForList(itemSqlId, parameter, offset, pageSize);// 数据项
			result = new Pagination(listItem, PaginationRuntime.getTotalCount(), offset, pageSize);
			return result;
		} catch (Exception e) {
			throw new SystemException("Page read data error。",ErrorCode.ERROR_CODE_DATABASE_PAGEQUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, int, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId, int currentPageIndex, int pageSize) {
		return queryForPaginatedList(itemSqlId,null,currentPageIndex,pageSize);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Class, int, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId,Class resultClass, int currentPageIndex, int pageSize) {
		try {
			ResultMap.setLocalClass(resultClass);
			Pagination result = null;
			if (currentPageIndex <= 0)
				currentPageIndex = 1;
			int offset = (currentPageIndex - 1) * pageSize;// 偏移值
			List<?> listItem = obtainSqlMapClientTemplate().queryForList(itemSqlId, offset, pageSize);// 数据项
			result = new Pagination(listItem, PaginationRuntime.getTotalCount(), offset, pageSize);
			return result;
		} catch (Exception e) {
			throw new SystemException("Page read data error。",ErrorCode.ERROR_CODE_DATABASE_PAGEQUERY_ERROR,e);
		}finally{
			ResultMap.removeLocalClass();
		}
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Class, java.lang.Object, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId,Class resultClass, Object parameter, int currentPageIndex) {
		return queryForPaginatedList(itemSqlId, resultClass, parameter, currentPageIndex, Pagination.PAGESIZE);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Object, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex) {
		return queryForPaginatedList(itemSqlId, parameter, currentPageIndex, Pagination.PAGESIZE);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, java.lang.Class, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId,Class resultClass, int currentPageIndex) {
		return queryForPaginatedList(itemSqlId, resultClass, currentPageIndex, Pagination.PAGESIZE);
	}
	
	 * 查看接口的注释描述
	 * @see com.framework.service.dao.ibatis.GeneralDAO#queryForPaginatedList(java.lang.String, int)
	 
	@Override
	public Pagination queryForPaginatedList(String itemSqlId, int currentPageIndex) {
		return queryForPaginatedList(itemSqlId, currentPageIndex, Pagination.PAGESIZE);
	}

	
*/}
