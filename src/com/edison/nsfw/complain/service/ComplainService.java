package com.edison.nsfw.complain.service;



import java.util.List;
import java.util.Map;

import com.edison.core.service.BaseService;
import com.edison.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
		//�Զ�����Ͷ��
		public void autoDeal();
		/**
		 * ������ݻ�ȡͳ����ȵ�ÿ���µ�Ͷ����
		 * @param year Ҫͳ�Ƶ����
		 * @return ͳ������
		 */
		public List<Map> getAnnualStatisticDataByYear(int year);
}
