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
		
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		try {
			QueryHelper queryHelper=new QueryHelper(Complain.class, "c");
			//开始时间查询
			if(StringUtils.isNotBlank(startTime)){//查询开始时间之后的投诉数据
				startTime = URLDecoder.decode(startTime, "utf-8");
				queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
			}
			if(StringUtils.isNotBlank(endTime)){//查询结束时间之前的投诉数据
				endTime = URLDecoder.decode(endTime, "utf-8");
				queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
			}
			if(complain != null){//接收到前端的条件，封装到complain对象中
				//状态       这个没有标题搜索耗费资源，所以写在前面
				if(StringUtils.isNotBlank(complain.getState())){
					queryHelper.addCondition("c.state=?", complain.getState());
				}
				//标题搜索
				if(StringUtils.isNotBlank(complain.getCompTitle())){
					complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
					queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
				}
			}
			//安装状态升序排序
			queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
			//按照投诉时间升序排序
			queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
			
			pageResult=complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listUI";
	
	}
	
	//跳转到受理页面
		public String dealUI(){
			//加载状态集合
			ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
			if(complain != null){
				//在listUI页面点击受理，传递要受理的投诉ID，通过id后台获得complain，然后在dealUI页面显示
				complain = complainService.findObjectById(complain.getCompId());
			}
			return "dealUI";
		}
		
		public String deal(){
			//dealUI页面隐藏的传入投诉信息的id，
			if(complain!=null){
				//通过id查询到投诉信息
				Complain tem=complainService.findObjectById(complain.getCompId());
				//reply从dealUI中获得
				if(reply!=null){
					//页面中没有回复的时间，服务器为其加上回复的时间
					reply.setReplyTime(new Timestamp(new Date().getTime()));
					//这条回复为根据id查询出来的投诉的回复
					reply.setComplain(tem);
					//投诉信息也添加回复   一条投诉信息可以有多个回复，所以map保存回复
					tem.getComplainReplies().add(reply);
				}
				//更新投死信息
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
