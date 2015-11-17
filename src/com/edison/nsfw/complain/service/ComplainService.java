package com.edison.nsfw.complain.service;

import com.edison.core.service.BaseService;
import com.edison.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
		//自动受理投诉
		public void autoDeal();
}
