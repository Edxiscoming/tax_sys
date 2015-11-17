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
		Calendar cal = Calendar.getInstance();//获取当前时间
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置当前时间的日期为1号
		cal.set(Calendar.HOUR_OF_DAY, 0);//设置当前时间的日期为1号,0时
		cal.set(Calendar.MINUTE, 0);//设置当前时间的日期为1号,0分
		cal.set(Calendar.SECOND, 0);//设置当前时间的日期为1号,0秒
		
		//1、查询本月之前的待受理的投诉列表
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime());//本月1号0时0分0秒
		
		List<Complain> list = findObjects(queryHelper);
		if(list != null && list.size() > 0){
			//2、更新投诉信息的状态为 已失效
			for(Complain comp: list){
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				update(comp);
			}
		}
	}
	
	}


