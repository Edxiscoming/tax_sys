package com.edison.nsfw.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.management.InstanceNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.edison.core.action.BaseAction;
import com.edison.core.page.PageResult;
import com.edison.core.util.QueryHelper;
import com.edison.nsfw.info.entity.Info;
import com.edison.nsfw.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class InfoAction extends BaseAction {
	
	@Resource
	private InfoService infoService;
	private Info info;
	private String[] privilegeIds;
	private String strTitle;
	
	//�б�ҳ��
	public String listUI() throws Exception{
		//���ط��༯��
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper=new QueryHelper(Info.class, "i");
		try {
			if(info!=null){
				if(StringUtils.isNotBlank(info.getTitle())){
					info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
					queryHelper.addCondition("i.title like ?", "%"+info.getTitle()+"%");
				}
			}
			//��������
			queryHelper.addOrderByProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
			//infoList = infoService.findObjects(queryHelper);
			//��ҳ��ѯ
			pageResult = infoService.getPageResult(queryHelper,getPageNo(),getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "listUI";
	}
	//��ת������ҳ��
	public String addUI(){
		//���ط��༯��
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		//ת����addUIҳ���У���ʾʱ�䡣
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		
		return "addUI";
	}
	//��������
	public String add(){
		try {
			if(info != null){
				infoService.save(info);
			}
			info = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//��ת���༭ҳ��
	public String editUI(){
		//���ط��༯��
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			strTitle=info.getTitle();
			info = infoService.findObjectById(info.getInfoId());
		}
		return "editUI";
	}
	//����༭
	public String edit(){
		try {
			if(info != null){
				infoService.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//ɾ��
	public String delete(){
		if(info != null && info.getInfoId() != null){
			infoService.delete(info.getInfoId());
			strTitle=info.getTitle();
		}
		return "list";
	}
	//����ɾ��
	public String deleteSelected(){
		if(selectedRow != null){
			strTitle=info.getTitle();
			for(String id: selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	//�첽������Ϣ
	public void publicInfo(){
		try {
			if(info != null){
				//1��������Ϣ״̬
				Info tem = infoService.findObjectById(info.getInfoId());
				tem.setState(info.getState());
				infoService.update(tem);
				
				//2��������½��
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("����״̬�ɹ�".getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	
}
