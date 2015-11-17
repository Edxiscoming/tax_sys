package com.edison.nsfw.complain.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edison.core.service.impl.BaseServiceImpl;
import com.edison.core.util.QueryHelper;
import com.edison.nsfw.complain.dao.ComplainDao;
import com.edison.nsfw.complain.entity.Complain;
import com.edison.nsfw.complain.service.ComplainService;


@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {
	
	private ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	@Override
	public void autoDeal() {
		Calendar cal = Calendar.getInstance();//��ȡ��ǰʱ��
		cal.set(Calendar.DAY_OF_MONTH, 1);//���õ�ǰʱ�������Ϊ1��
		cal.set(Calendar.HOUR_OF_DAY, 0);//���õ�ǰʱ�������Ϊ1��,0ʱ
		cal.set(Calendar.MINUTE, 0);//���õ�ǰʱ�������Ϊ1��,0��
		cal.set(Calendar.SECOND, 0);//���õ�ǰʱ�������Ϊ1��,0��
		
		//1����ѯ����֮ǰ�Ĵ������Ͷ���б�
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime());//����1��0ʱ0��0��
		
		List<Complain> list = findObjects(queryHelper);
		if(list != null && list.size() > 0){
			//2������Ͷ����Ϣ��״̬Ϊ ��ʧЧ
			for(Complain comp: list){
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				update(comp);
			}
		}
	}
	
	}


