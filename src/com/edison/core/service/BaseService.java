package com.edison.core.service;

import java.io.Serializable;
import java.util.List;

import com.edison.core.util.QueryHelper;
import com.edison.nsfw.info.entity.Info;

public interface BaseService<T> {
	//保存
	public void save(T entity);
	//更新
	public void update(T entity);
	//删除
	public void delete(Serializable id);
	//根据id查询
	public T findObjectById(Serializable id);
	//查询所有
	public List<T> findObjects();
	//根据条件查询不推荐使用
	@Deprecated
	public List<T> findObjects(String hql, List<Object> parameters);
	//条件查询 优化--queryHelper
	public List<T> findObjects(QueryHelper queryHelper);
}
