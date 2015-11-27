package com.edison.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import com.edison.core.dao.impl.BaseDaoImpl;
import com.edison.nsfw.complain.dao.ComplainDao;
import com.edison.nsfw.complain.entity.Complain;


public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	/**
	 * 根据年份获取统计年度的每个月的投诉数
	 * @param year 要统计的年份
	 * @return 统计数据
	 */
	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT imonth, COUNT(comp_id)")
		.append(" FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)")
		.append(" AND YEAR(comp_time)=?")
		.append(" GROUP BY imonth ")
		.append(" ORDER BY imonth");
		/**为什么使用SQLQuery：hibernate 中createQuery与createSQLQuery两者区别是：
			前者用的hql语句进行查询，后者可以用sql语句查询
			前者以hibernate生成的Bean为对象装入list返回
			后者则是以对象数组进行存储
			所以使用createSQLQuery有时候也想以hibernate生成的Bean为对象装入list返回，就不是很方便
			突然发现createSQLQuery有这样一个方法可以直接转换对象
		 * 
		 */
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setParameter(0, year);
		return sqlQuery.list();
	}

}
