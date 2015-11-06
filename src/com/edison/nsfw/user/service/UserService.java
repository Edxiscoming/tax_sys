package com.edison.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.edison.core.exception.ServiceException;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.entity.UserRole;

public interface UserService {
	    //新增
		public void save(User user);
		//更新
		public void update(User user);
		//根据id删除O
		public void delete(Serializable id);
		//根据id查找
		public User findObjectById(Serializable id);
		//查找列表
		public List<User> findObjects() throws ServiceException;
		public void exportExcel(List<User> userList,ServletOutputStream outputStream);
		public void importExcel(File userExcel,String userExcelFileName);
		public List<User> findObjectByIdAndAccount(String id, String account);
		//保存带有角色的用户
		public void saveUserAndRole(User user, String...roleIds );
		//更新带有角色的用户
		public void updateUserAndRole(User user, String... roleIds);
		//通过用户得到用户角色
		public List<UserRole> getUserRolesByUserId(String id);
		//验证登录
		public List<User> findUserByAccountAndPass(String account,String password);
}
