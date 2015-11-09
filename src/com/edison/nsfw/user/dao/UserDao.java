package com.edison.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import com.edison.core.dao.BaseDao;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	List<User> findObjectByIdAndAccount(String id, String account);

	void saveUserRole(UserRole userRole);

	void deleteUserRoleByUserId(Serializable id);

	List<UserRole> getUserRolesByUserId(String id);
    //ÑéÖ¤µÇÂ¼
	List<User> findUserByAccountAndPass(String account, String password);

}
