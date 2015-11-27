package com.edison.nsfw.complain.dao;

import java.util.List;

import com.edison.core.dao.BaseDao;
import com.edison.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {
	/**
	 * ������ݻ�ȡͳ����ȵ�ÿ���µ�Ͷ����
	 * @param year Ҫͳ�Ƶ����
	 * @return ͳ������
	 */
	public List<Object[]> getAnnualStatisticDataByYear(int year);

}
