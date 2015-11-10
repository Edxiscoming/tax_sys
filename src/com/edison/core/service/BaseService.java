package com.edison.core.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	//����
	public void save(T entity);
	//����
	public void update(T entity);
	//ɾ��
	public void delete(Serializable id);
	//����id��ѯ
	public T findObjectById(Serializable id);
	//��ѯ����
	public List<T> findObjects();

}
