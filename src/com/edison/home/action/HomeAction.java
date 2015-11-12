package com.edison.home.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspApplicationContext;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.edison.core.util.QueryHelper;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	private Map<String, Object> return_map;
	
	//��ת����ҳ
	@Override
	public String execute() throws Exception {
		return "home";
	}
	
	//��ת����ҪͶ��
	public String complainAddUI(){
		return "complainAddUI";
	}
	
	public void getUserJson(){
		try {
			//��ò���
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
			//��Ӳ�ѯ����
			QueryHelper queryHelper=new QueryHelper(User.class, "u");
			queryHelper.addCondition("u.dept like ?", "%"+dept);
			List<User> userList=userService.findObjects(queryHelper);
			//json
			JSONObject jso=new JSONObject();
			jso.put("msg","success");
			jso.accumulate("userList", userList);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream=response.getOutputStream();
			outputStream.write(jso.toString().getBytes("utf-8"));
			outputStream.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String getUserJson2(){
		try {
			//1����ȡ����
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" +dept);
				//2�����ݲ��Ų�ѯ�û��б�
				return_map = new HashMap<String, Object>();
				return_map.put("msg", "success");
				return_map.put("userList", userService.findObjects(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}
	
}
