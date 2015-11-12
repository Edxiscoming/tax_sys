package com.edison.nsfw.complain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edison.core.service.impl.BaseServiceImpl;
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

}
