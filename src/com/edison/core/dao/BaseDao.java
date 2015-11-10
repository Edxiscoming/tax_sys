package com.edison.core.dao;


import java.io.Serializable;
import java.util.List;

import com.edison.core.util.QueryHelper;

public interface BaseDao<T> {
	
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects();
	//根据条件查询
	public List<T> findObjects(String hql, List<Object> parameters);
	//优化条件查询，queryHelper
	public List<T> findObjects(QueryHelper queryHelper);

}
