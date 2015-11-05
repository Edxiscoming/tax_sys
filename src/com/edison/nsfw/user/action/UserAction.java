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
	//Ĭ��д��    ���ڵ����� 2015.11.4
	private String headImgFileName;
	
	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	
	//�б�ҳ��
	public String listUI() throws Exception {
		try {
			int i=1/0;
			//userList=userService.findObjects();
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
		return "listUI";
	}
	//��ת������ҳ��
	public String addUI() {
		return "addUI";
	}
	//��������
	public String add(){
		try {
			if(user!=null){
				if(headImg!=null){
					//1������ͷ��upload/user
					//��ȡ����·���ľ��Ե�ַ
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					//�ļ������֣�ʹ��uuid�����ã��������Ա�������
					String fileName=UUID.randomUUID().toString().replace("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					
					//�����ļ������ļ����Ƶ�ָ��Ŀ¼
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					
					//����ͷ��·��
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
	//��ת���༭ҳ��
	public String editUI() {
		if(user!=null&&user.getId()!=null){
			user =userService.findObjectById(user.getId());
		}
		return "editUI";
	}
	//����༭
	public String edit() {
		try {
			if(user!=null){
				//����ͷ��
				if(headImg != null){
					//1������ͷ��upload/user
					//��ȡ����·���ľ��Ե�ַ
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//�����ļ�
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					
					//2�������û�ͷ��·��
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
	//ɾ��
	public String delete() {
		if(user!=null&&user.getId()!=null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//����ɾ��
	public String deleteSelected() {
		if(selectedRow!=null){
		for(String id : selectedRow){
			userService.delete(id);
		}
		}
		return "list";
	}
	//����excel
	public void exportExcel() {
		try {
			//1�������û��б�
			userList = userService.findObjects();
			//2������
			//��ȡ��Ӧ
			HttpServletResponse response = ServletActionContext.getResponse();
			//�ļ�������excel
			response.setContentType("application/x-execl");
			//���ص���ʽ���Ը�������ʽ����ָ������������                                                        ��Ϊ�����ģ�ϣ��������������ж���ʾ����������ת��
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("�û��б�.xls".getBytes(), "ISO-8859-1"));
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
		//1����ȡexcel�ļ�
				if(userExcel != null){
					//�Ƿ���excel
					if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
						//2������
						userService.importExcel(userExcel, userExcelFileName);
					}
				}
				return "list";
	}
	public void verifyAccount(){//����void����Ϊ��action�����еķ���ֵ���Ǹ�struts���ص�
		try {
			//��ȡ�˺�
			if(user!=null&&StringUtils.isNotBlank(user.getAccount())){
			//�����˺Ų�ѯ���ݿ����Ƿ�������˺�
				List<User> list=userService.findObjectByIdAndAccount(user.getId(),user.getAccount());
			//���巵�ظ����������Ϣ
				String strResult="true";
				if(list!=null&&list.size()>0){
					//˵�����ʺ��Ѿ�����
					strResult="false";
				}
			//���ͷ�����Ϣ�������  ��action������Ϣ�������  �ص�
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
