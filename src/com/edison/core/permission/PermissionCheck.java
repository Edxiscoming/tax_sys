package com.edison.core.permission;

import com.edison.nsfw.user.entity.User;

public interface PermissionCheck {
	//查看用户是否有权限
	public boolean isAceesiable(User user, String code);
	
}
