package com.edison.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import com.edison.core.permission.PermissionCheck;
import com.edison.nsfw.role.entity.Role;
import com.edison.nsfw.role.entity.RolePrivilege;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.entity.UserRole;
import com.edison.nsfw.user.service.UserService;
import com.sun.org.apache.bcel.internal.classfile.Code;

public class PermissionCheckImpl implements PermissionCheck {
	
	@Resource
	private UserService userService;
	
	@Override
	public boolean isAceesiable(User user, String code) {
		//1����ȡ�û������н�ɫ
				List<UserRole> list = user.getUserRoles();
		if(list==null){	
			list=userService.getUserRolesByUserId(user.getId());
		}
		if(list!=null&&list.size()>0){
			//����
			for(UserRole ur:list){
				Role role=ur.getId().getRole();
				for(RolePrivilege rp:role.getRolePrivileges()){
					if(code.equals(rp.getId().getCode())){
						//�û����н�����˰�����Ȩ��
						return true;
					}
				}
			}
		}
		return false;
	}

}
