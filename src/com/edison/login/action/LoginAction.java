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
	
	//跳转到登录页面
		public String toLoginUI(){
			return "loginUI";
		}
		//验证登录
		public String login(){
			if(user!=null){
				if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
					List<User> list=userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
					if(list!=null&&list.size()>0){
						//登录成功
						User user=list.get(0);
						//根据用户id得到用户的角色权限
						user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
						ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
						Log log = LogFactory.getLog(getClass());
						log.info("用户名称为：" + user.getName() + " 的用户登录了系统。");
						//2.1.4、重定向跳转到首页
						return "home";
					}else{
						//登录不成功
						loginResult="帐号或密码不正确！";
					}
				}else{
					loginResult="账号和密码不能为空";
				}
			}else{
				loginResult="请输入帐号和密码！";
			}
			return toLoginUI();
		}
		//注销
		public String logout(){
			ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
			return toLoginUI();
		}
		//跳转到没有权限提示页面
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
