package com.edison.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.edison.nsfw.user.entity.User;

public interface UserService {
	    //����
		public void save(User user);
		//����
		public void update(User user);
		//����idɾ��O
		public void delete(Serializable id);
		//����id����
		public User findObjectById(Serializable id);
		//�����б�
		public List<User> findObjects();
		public void exportExcel(List<User> userList,ServletOutputStream outputStream);
		public void importExcel(File userExcel,String userExcelFileName);
}
