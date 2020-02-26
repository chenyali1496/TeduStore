package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * 如果用户没有登陆就尝试访问某些URL，将被重定向到某些页面
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, 
			Object handler)
			throws Exception {
		//从request中获取session
		HttpSession session=request.getSession();
		//判断session中是否存在id
		if(session.getAttribute("id")!=null) {
		//存在：用户已登录，则放行
			return true;
		}else {
		//不存在：用户未登录，重定向，且拦截
			response.sendRedirect("../web/login.html");
			return false;
		}
	}

	
	
	
	
	
	
	
	
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
