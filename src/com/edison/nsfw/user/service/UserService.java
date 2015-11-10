package com.edison.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.edison.core.exception.ServiceException;
import com.edison.core.service.BaseService;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{
	   
		public void exportExcel(List<User> userList,ServletOutputStream outputStream);
		public void importExcel(File userExcel,String userExcelFileName);
		public List<User> findObjectByIdAndAccount(String id, String account);
		//������н�ɫ���û�
		public void saveUserAndRole(User user, String...roleIds );
		//���´��н�ɫ���û�
		public void updateUserAndRole(User user, String... roleIds);
		//ͨ���û��õ��û���ɫ
		public List<UserRole> getUserRolesByUserId(String id);
		//��֤��¼
		public List<User> findUserByAccountAndPass(String account,String password);
}
