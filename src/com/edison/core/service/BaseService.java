package com.edison.core.service;

import java.io.Serializable;
import java.util.List;

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

}
