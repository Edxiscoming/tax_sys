package com.edison.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.UserException;

import com.edison.core.constant.Constant;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	
	//��ת����¼ҳ��
		public String toLoginUI(){
			return "loginUI";
		}
		//��֤��¼
		public String login(){
			if(user!=null){
				if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
					List<User> list=userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
					if(list!=null&&list.size()>0){
						//��¼�ɹ�
						User user=list.get(0);
						//�����û�id�õ��û��Ľ�ɫȨ��
						user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
						ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
						Log log = LogFactory.getLog(getClass());
						log.info("�û�����Ϊ��" + user.getName() + " ���û���¼��ϵͳ��");
						//2.1.4���ض�����ת����ҳ
						return "home";
					}else{
						//��¼���ɹ�
						loginResult="�ʺŻ����벻��ȷ��";
					}
				}else{
					loginResult="�˺ź����벻��Ϊ��";
				}
			}else{
				loginResult="�������ʺź����룡";
			}
			return toLoginUI();
		}
		//ע��
		public String logout(){
			ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
			return toLoginUI();
		}
		//��ת��û��Ȩ����ʾҳ��
		public String toNoPermissionUI(){
			return "noPermissionUI";
		}
		public UserService getUserService() {
			return userService;
		}
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public String getLoginResult() {
			return loginResult;
		}
		public void setLoginResult(String loginResult) {
			this.loginResult = loginResult;
		}
		
		
}
