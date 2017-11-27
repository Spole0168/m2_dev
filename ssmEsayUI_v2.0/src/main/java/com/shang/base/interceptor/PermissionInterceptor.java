package com.shang.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shang.dal.model.User;

public class PermissionInterceptor implements HandlerInterceptor {
	protected Logger logger = Logger.getLogger(this.getClass());
	//不拦截的请求
	private static final String[] IGNORE_URI = {"login","reg","getCheckCodeImage","logout"};
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		String path = req.getServletPath();
		for(String uri : IGNORE_URI){
			if(path.contains(uri)){
				return true;
			}
		}
		//需要拦截的请求
		User user = (User) req.getSession().getAttribute("currentUser");
		if(null!=user){
			return true;
		}
		req.getRequestDispatcher("user/logout.do").forward(req, res);
		return false;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		logger.info("PermissionInterceptor -->  afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}


}
