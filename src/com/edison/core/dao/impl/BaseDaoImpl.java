package com.edison.core.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edison.core.dao.BaseDao;
import com.edison.core.util.QueryHelper;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	Class<T> clazz;
	//反射获得父类泛型，得到类名
	public BaseDaoImpl(){
		ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		//HibernateDaoSupport直接有getSession()这个方法
		Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

	//根据条件查询
	@Override
	public List<T> findObjects(String hql,List<Object> parameters) {
		//HibernateDaoSupport直接有getSession()这个方法
		Query query = getSession().createQuery(hql);
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		//HibernateDaoSupport直接有getSession()这个方法
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters=queryHelper.getParameters();
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
	return query.list();
	}

	
}
