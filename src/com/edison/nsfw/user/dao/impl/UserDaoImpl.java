package com.edison.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;







import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.edison.core.dao.impl.BaseDaoImpl;
import com.edison.nsfw.user.dao.UserDao;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User>implements UserDao {

	@Override
	public List<User> findObjectByIdAndAccount(String id, String account) {
		
		String hql="From User WHERE account=?";
		if(StringUtils.isNotBlank(id)){
			hql+="and id!=?";
		}
		
		Query query=getSession().createQuery(hql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}	
	
	
	@Override
	public void deleteUserRoleByUserId(String id) {
		Query query=getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		Query query=getSession().createQuery("FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		Query query=getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}
}
