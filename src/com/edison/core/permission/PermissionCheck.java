package com.edison.core.permission;

import com.edison.nsfw.user.entity.User;

public interface PermissionCheck {
	//�鿴�û��Ƿ���Ȩ��
	public boolean isAceesiable(User user, String code);
	
}
