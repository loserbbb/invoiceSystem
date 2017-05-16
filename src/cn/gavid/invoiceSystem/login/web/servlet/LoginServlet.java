package cn.gavid.invoiceSystem.login.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gavid.invoiceSystem.login.domain.User;
import cn.gavid.invoiceSystem.login.service.UserException;
import cn.gavid.invoiceSystem.login.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class LoginServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//封装表单数据
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		boolean x = request.getParameter("remmberpwd") != null;
		user.setuRemmberpwd(x);
		try {
			//创建错误集合
			Map<String,String> error = new HashMap<String, String>();
			//校验用户名和密码
			String username = user.getuName();
			String pwd = user.getuPassword();
			if(username == null||username.trim().isEmpty())
				error.put("username", "用户名不能为空！！");
			if(pwd == null||pwd.trim().isEmpty())
				error.put("pwd", "密码不能为空！！");
			if(error.size()>0){
				request.setAttribute("errors", error);
				return "f:/login.jsp";
			}
				
			//登录成功！
			User session_User = userService.login(user);
			request.getSession().setAttribute("session_user", session_User);
			return "f:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msgs", e.getMessage());
			return "f:/login.jsp";
		}
	}
}
