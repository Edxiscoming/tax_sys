package com.edison.nsfw.user.dao;

import java.util.List;

import com.edison.core.dao.BaseDao;
import com.edison.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User> {

	List<User> findObjectByIdAndAccount(String id, String account);

}
