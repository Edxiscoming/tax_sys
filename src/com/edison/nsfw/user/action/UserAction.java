package com.edison.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	private File headImg;
	private String headImgContentType;
	//默认写法    现在的理解哈 2015.11.4
	private String headImgFileName;
	
	
	//列表页面
	public String listUI() {
		userList=userService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI() {
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(user!=null){
				if(headImg!=null){
					//1、保存头像到upload/user
					//获取保存路径的绝对地址
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					//文件的名字，使用uuid在设置，这样可以避免重名
					String fileName=UUID.randomUUID().toString().replace("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					
					//复制文件，把文件复制到指定目录
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					
					//设置头像路径
					user.setHeadImg("user/"+fileName);
				}
				userService.save(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI() {
		if(user!=null&&user.getId()!=null){
			user =userService.findObjectById(user.getId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit() {
		if(user!=null){
			userService.update(user);
		}
		return "list";
	}
	//删除
	public String delete() {
		if(user!=null&&user.getId()!=null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected() {
		if(selectedRow!=null){
		for(String id : selectedRow){
			userService.delete(id);
		}
		}
		
		return "list";
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	
}
