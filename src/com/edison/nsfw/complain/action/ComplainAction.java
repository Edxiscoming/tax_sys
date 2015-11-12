package com.edison.nsfw.complain.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import sun.security.krb5.internal.rcache.ReplayCache;

import com.edison.core.action.BaseAction;
import com.edison.core.util.QueryHelper;
import com.edison.nsfw.complain.entity.Complain;
import com.edison.nsfw.complain.entity.ComplainReply;
import com.edison.nsfw.complain.service.ComplainService;
import com.opensymphony.xwork2.ActionContext;

public class ComplainAction extends BaseAction {
	
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply reply;
	
	public String listUI(){
		
		//����״̬����
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		try {
			QueryHelper queryHelper=new QueryHelper(Complain.class, "c");
			//��ʼʱ���ѯ
			if(StringUtils.isNotBlank(startTime)){//��ѯ��ʼʱ��֮���Ͷ������
				startTime = URLDecoder.decode(startTime, "utf-8");
				queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
			}
			if(StringUtils.isNotBlank(endTime)){//��ѯ����ʱ��֮ǰ��Ͷ������
				endTime = URLDecoder.decode(endTime, "utf-8");
				queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
			}
			if(complain != null){//���յ�ǰ�˵���������װ��complain������
				//״̬       ���û�б��������ķ���Դ������д��ǰ��
				if(StringUtils.isNotBlank(complain.getState())){
					queryHelper.addCondition("c.state=?", complain.getState());
				}
				//��������
				if(StringUtils.isNotBlank(complain.getCompTitle())){
					complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
					queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
				}
			}
			//��װ״̬��������
			queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
			//����Ͷ��ʱ����������
			queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
			
			pageResult=complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listUI";
	
	}
	
	//��ת������ҳ��
		public String dealUI(){
			//����״̬����
			ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
			if(complain != null){
				//��listUIҳ������������Ҫ�����Ͷ��ID��ͨ��id��̨���complain��Ȼ����dealUIҳ����ʾ
				complain = complainService.findObjectById(complain.getCompId());
			}
			return "dealUI";
		}
		
		public String deal(){
			//dealUIҳ�����صĴ���Ͷ����Ϣ��id��
			if(complain!=null){
				//ͨ��id��ѯ��Ͷ����Ϣ
				Complain tem=complainService.findObjectById(complain.getCompId());
				//reply��dealUI�л��
				if(reply!=null){
					//ҳ����û�лظ���ʱ�䣬������Ϊ����ϻظ���ʱ��
					reply.setReplyTime(new Timestamp(new Date().getTime()));
					//�����ظ�Ϊ����id��ѯ������Ͷ�ߵĻظ�
					reply.setComplain(tem);
					//Ͷ����ϢҲ��ӻظ�   һ��Ͷ����Ϣ�����ж���ظ�������map����ظ�
					tem.getComplainReplies().add(reply);
				}
				//����Ͷ����Ϣ
				complainService.update(tem);
			}
			return "list";
		}
	public ComplainService getComplainService() {
		return complainService;
	}
	public void setComplainService(ComplainService complainService) {
		this.complainService = complainService;
	}
	public Complain getComplain() {
		return complain;
	}
	public void setComplain(Complain complain) {
		this.complain = complain;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public ComplainReply getReply() {
		return reply;
	}
	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}
	
}
