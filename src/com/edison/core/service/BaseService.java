package com.edison.core.service;

import java.io.Serializable;
import java.util.List;

import com.edison.core.util.QueryHelper;
import com.edison.nsfw.info.entity.Info;

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
	//����������ѯ���Ƽ�ʹ��
	@Deprecated
	public List<T> findObjects(String hql, List<Object> parameters);
	//������ѯ �Ż�--queryHelper
	public List<T> findObjects(QueryHelper queryHelper);
}
