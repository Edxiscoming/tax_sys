package com.edison.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.edison.core.constant.Constant;
import com.edison.core.permission.PermissionCheck;
import com.edison.nsfw.user.entity.User;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		//�õ����ʵ�ַ��uri
		String uri=request.getRequestURI();
		//��֤��¼
		if (!uri.contains("sys/login_")) {
			//�ǵ�¼����Ҫ��֤
			//��session��ȡ���û�
			if(request.getSession().getAttribute(Constant.USER)!=null){
				//˵���Ѿ���¼
				//��Ҫ�ж��û�Ȩ��,�ж��ǲ�����˰������ϵͳ
				if(uri.contains("/nsfw/")){
					//������˰������ϵͳ,�õ��û���Ϣ
					User user=(User) request.getSession().getAttribute(Constant.USER);
					//�õ�spring����
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					
					PermissionCheck pc=(PermissionCheck) applicationContext.getBean("permissionCheck");
					if(pc.isAceesiable(user,"nsfw")){
						//�û���Ȩ�޿��Լ�����˰������ϵͳ
						chain.doFilter(request, response);
					}else{
						//�û���Ȩ������˰����
						response.sendRedirect(request.getContextPath() + "/sys/login_toNoPermissionUI.action");
					}
				}else {
					//�Ƿ�����˰������ϵͳ����ֱ�ӷ���
					chain.doFilter(request, response);
				}
			}else{
				//session����user��û�е�¼��ֱ���ض��򵽵�¼����
				response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}else{
			//�Ѿ���¼�����С�
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
