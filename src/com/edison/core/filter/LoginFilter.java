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
		//得到访问地址的uri
		String uri=request.getRequestURI();
		//验证登录
		if (!uri.contains("sys/login_")) {
			//非登录，需要验证
			//从session中取出用户
			if(request.getSession().getAttribute(Constant.USER)!=null){
				//说明已经登录
				//还要判断用户权限,判断是不是纳税服务子系统
				if(uri.contains("/nsfw/")){
					//访问纳税服务子系统,得到用户信息
					User user=(User) request.getSession().getAttribute(Constant.USER);
					//得到spring容器
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					
					PermissionCheck pc=(PermissionCheck) applicationContext.getBean("permissionCheck");
					if(pc.isAceesiable(user,"nsfw")){
						//用户有权限可以计入纳税服务子系统
						chain.doFilter(request, response);
					}else{
						//用户无权进入纳税服务
						response.sendRedirect(request.getContextPath() + "/sys/login_toNoPermissionUI.action");
					}
				}else {
					//非访问纳税服务子系统，则直接放行
					chain.doFilter(request, response);
				}
			}else{
				//session中无user，没有登录，直接重定向到登录界面
				response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}else{
			//已经登录，放行。
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
