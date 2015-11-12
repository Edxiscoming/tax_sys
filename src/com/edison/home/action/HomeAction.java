package com.edison.home.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
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
import com.edison.nsfw.complain.entity.Complain;
import com.edison.nsfw.complain.service.ComplainService;
import com.edison.nsfw.user.entity.User;
import com.edison.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	@Resource
	private ComplainService complainService;
	private Complain comp;
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
	
	//����Ͷ��
		public void complainAdd(){
			try {
				if(comp != null){
					//����ĬдͶ��״̬Ϊ ������
					comp.setState(Complain.COMPLAIN_STATE_UNDONE);
					comp.setCompTime(new Timestamp(new Date().getTime()));
					complainService.save(comp);
					
					//���Ͷ�߽��
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write("success".getBytes("utf-8"));
					outputStream.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

	public Complain getComp() {
		return comp;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}
	
}
