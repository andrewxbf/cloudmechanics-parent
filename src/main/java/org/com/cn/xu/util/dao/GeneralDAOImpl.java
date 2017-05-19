package org.com.cn.xu.util.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.com.cn.xu.util.Pagination;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GeneralDAOImpl extends SqlSessionDaoSupport implements GeneralDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@PostConstruct
	private void init(){
		setSqlSessionTemplate(sqlSession);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryObject(String sqlId) {
		return (T)getSqlSession().selectOne(sqlId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryObject(String sqlId, Object param) {
		return (T)getSqlSession().selectOne(sqlId,param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryForList(String sqlId) {
		return (T)getSqlSession().selectList(sqlId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryForList(String sqlId, Object param) {
		return (T)getSqlSession().selectList(sqlId,param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryObject(String sqlId, Class resultClass) {
		return (T)getSqlSession().selectOne(sqlId);
	} 

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryObject(String sqlId, Class resultClass, Object param) {
		return (T)getSqlSession().selectOne(sqlId,param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryForList(String sqlId, Class resultClass) {
		return (T)getSqlSession().selectList(sqlId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryForList(String sqlId, Class resultClass, Object param) {
		return (T)getSqlSession().selectList(sqlId,param);
	}

	@Override
	public int insertObject(String sqlId) {
		return getSqlSession().insert(sqlId);
	}

	@Override
	public int insertObject(String sqlId, Object param) {
		return getSqlSession().insert(sqlId,param);
	}

	@Override
	public int deleteObject(String sqlId) {
		return getSqlSession().delete(sqlId);
	}

	@Override
	public int deleteObject(String sqlId, Object param) {
		return getSqlSession().delete(sqlId,param);
	}

	@Override
	public int updateObject(String sqlId) {
		return getSqlSession().update(sqlId);
	}

	@Override
	public int updateObject(String sqlId, Object param) {
		return getSqlSession().update(sqlId,param);
	}

	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId, Object parameter,
											   int currentPageIndex, int pageSize) {
		return doQueryForPaginatedList(itemSqlId, parameter, currentPageIndex, pageSize,null);
	}

    @Override
    public Pagination queryForPaginatedList(String itemSqlId, Object parameter, int currentPageIndex, int pageSize, String sort,String order) {
        String orderby = ((sort == null|| "".equals(sort)) ? null : (order == null ? sort : sort+" "+order));
        return doQueryForPaginatedList(itemSqlId, parameter, currentPageIndex, pageSize,orderby);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Pagination doQueryForPaginatedList(String itemSqlId, Object parameter,int currentPageIndex, int pageSize,String orderby) {
		PageHelper.startPage(currentPageIndex, pageSize);
        if (null != orderby){
            PageHelper.orderBy(orderby);
        }
		List<Object> pageList = null;
		if(parameter != null){
			pageList = getSqlSession().selectList(itemSqlId, parameter);
		} else {
			pageList = getSqlSession().selectList(itemSqlId);
		}
		if (currentPageIndex <= 0) {
			int currentPageIndexTmp = 1;
			currentPageIndex = currentPageIndexTmp;
		}
		int offset = (currentPageIndex - 1) * pageSize;// 偏移值
		if(CollectionUtils.isEmpty(pageList)){
			return new Pagination(new ArrayList<Object>(), 0, offset,pageSize);
		}
		Page page = (Page)pageList;
		return new Pagination(getItems(page), (int)page.getTotal(), offset, pageSize);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Object> getItems(Page page){
		List<Object> items = new ArrayList(); 
		Iterator<Object> itItems = page.iterator();
		while(itItems.hasNext()){
			items.add(itItems.next());
		}
		return items;
	}

	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,int currentPageIndex, int pageSize) {
		return doQueryForPaginatedList(itemSqlId, null, currentPageIndex, pageSize,null);
	}
	
	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId, Object parameter,int currentPageIndex) {
		return doQueryForPaginatedList(itemSqlId, parameter, currentPageIndex, Pagination.PAGESIZE,null);
	}

	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,int currentPageIndex) {
		return doQueryForPaginatedList(itemSqlId, null, currentPageIndex, Pagination.PAGESIZE,null);
	}

	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,Class resultClass, Object parameter, int currentPageIndex,
			int pageSize) {
		return doQueryForPaginatedList(itemSqlId,  parameter, currentPageIndex, pageSize,null);
	}
	
	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,Class resultClass, int currentPageIndex, int pageSize) {
		return doQueryForPaginatedList(itemSqlId,  null, currentPageIndex, pageSize,null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,
			Class resultClass, Object parameter, int currentPageIndex) {
		return doQueryForPaginatedList(itemSqlId,  parameter, currentPageIndex, Pagination.PAGESIZE,null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pagination<?> queryForPaginatedList(String itemSqlId,
			Class resultClass, int currentPageIndex) {
		return doQueryForPaginatedList(itemSqlId, null, currentPageIndex, Pagination.PAGESIZE,null);
	}

}
