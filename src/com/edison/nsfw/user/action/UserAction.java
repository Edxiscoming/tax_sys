package com.edison.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.edison.core.exception.ActionException;
import com.edison.core.exception.ServiceException;
import com.edison.core.exception.SysException;
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
	
	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	
	//列表页面
	public String listUI() throws Exception {
		try {
			int i=1/0;
			//userList=userService.findObjects();
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
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
		try {
			if(user!=null){
				//处理头像
				if(headImg != null){
					//1、保存头像到upload/user
					//获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//复制文件
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					
					//2、设置用户头像路径
					user.setHeadImg("user/" + fileName);
				}
				userService.update(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	//导入excel
	public void exportExcel() {
		try {
			//1、查找用户列表
			userList = userService.findObjects();
			//2、导出
			//获取响应
			HttpServletResponse response = ServletActionContext.getResponse();
			//文件类型是excel
			response.setContentType("application/x-execl");
			//下载的形式是以附件的形式，并指定附件的名称                                                        因为有中文，希望在所有浏览器中都显示正常，所以转码
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userList, outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String importExcel() {
		//1、获取excel文件
				if(userExcel != null){
					//是否是excel
					if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
						//2、导入
						userService.importExcel(userExcel, userExcelFileName);
					}
				}
				return "list";
	}
	public void verifyAccount(){//返回void，因为在action中所有的返回值都是给struts拦截的
		try {
			//获取账号
			if(user!=null&&StringUtils.isNotBlank(user.getAccount())){
			//更具账号查询数据库中是否有这个账号
				List<User> list=userService.findObjectByIdAndAccount(user.getId(),user.getAccount());
			//定义返回给浏览器的信息
				String strResult="true";
				if(list!=null&&list.size()>0){
					//说明该帐号已经存在
					strResult="false";
				}
			//发送返回信息到浏览器  从action返回信息到浏览器  重点
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream=response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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

	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	
}
