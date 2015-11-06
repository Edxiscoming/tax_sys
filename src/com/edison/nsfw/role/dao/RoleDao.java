package com.edison.nsfw.role.dao;

import com.edison.core.dao.BaseDao;
import com.edison.nsfw.role.entity.Role;


public interface RoleDao extends BaseDao<Role> {

	//删除该角色对于的所有权限
	public void deleteRolePrivilegeByRoleId(String roleId);

}
