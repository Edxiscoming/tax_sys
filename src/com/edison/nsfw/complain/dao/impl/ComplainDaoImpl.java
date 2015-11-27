package com.edison.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import com.edison.core.dao.impl.BaseDaoImpl;
import com.edison.nsfw.complain.dao.ComplainDao;
import com.edison.nsfw.complain.entity.Complain;


public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	/**
	 * ������ݻ�ȡͳ����ȵ�ÿ���µ�Ͷ����
	 * @param year Ҫͳ�Ƶ����
	 * @return ͳ������
	 */
	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT imonth, COUNT(comp_id)")
		.append(" FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)")
		.append(" AND YEAR(comp_time)=?")
		.append(" GROUP BY imonth ")
		.append(" ORDER BY imonth");
		/**Ϊʲôʹ��SQLQuery��hibernate ��createQuery��createSQLQuery���������ǣ�
			ǰ���õ�hql�����в�ѯ�����߿�����sql����ѯ
			ǰ����hibernate���ɵ�BeanΪ����װ��list����
			���������Զ���������д洢
			����ʹ��createSQLQuery��ʱ��Ҳ����hibernate���ɵ�BeanΪ����װ��list���أ��Ͳ��Ǻܷ���
			ͻȻ����createSQLQuery������һ����������ֱ��ת������
		 * 
		 */
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setParameter(0, year);
		return sqlQuery.list();
	}

}
