package com.edison.nsfw.complain.dao;

import java.util.List;

import com.edison.core.dao.BaseDao;
import com.edison.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {
	/**
	 * 根据年份获取统计年度的每个月的投诉数
	 * @param year 要统计的年份
	 * @return 统计数据
	 */
	public List<Object[]> getAnnualStatisticDataByYear(int year);

}
