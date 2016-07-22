package cn.zhao.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhao.Service.UserService;
import cn.zhao.entity.User;

public class UserServlet extends HttpServlet {

	private UserService userService = new UserService();

	public Map<String, String> errors = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = (String) req.getParameter("flag");
		if ("user_register".equals(flag)) {
			System.out.println("进入注册servlet！");
			String username = (String) req.getParameter("userName");
			String password = (String) req.getParameter("passWord");
			String repassword = (String) req.getParameter("rePassWord");
			String email = (String) req.getParameter("email");
			boolean isWrong = true;
			while (true) {
				if (userService.getUserByName(username) != null) {
					isWrong = false;
					errors.put("p_username", "用户名已被注册！");
				}
				if (!password.matches("\\w{6,15}")) {
					isWrong = false;
					errors.put("p_password", "密码过于简单！");
				}
				if (!password.equals(repassword)) {
					isWrong = false;
					errors.put("p_repassword", "两次输入的密码不一样!");
				}
				if (!email.matches("^(?:\\w+\\.{1})*\\w+@(\\w+\\.)*\\w+$")) {
					isWrong = false;
					errors.put("p_email", "邮箱格式不正确！");
				}
				if (!isWrong) {
					req.setAttribute("errors", errors);
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
				break;
			}
			if (isWrong) {
				User user = new User();
				user.setUserName(username);
				user.setPassword(password);
				user.setEmail(email);
				if (userService.register(user)) {
					HttpSession session = req.getSession();
					session.setAttribute("loginName", username);
					resp.sendRedirect("register_success.html");
				} else {
					errors.put("message", "注册失败，用户名已经存在！");
					req.setAttribute("errrs", errors);
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
			}
		} else if ("user_login".equals(flag)) {
			System.out.println("进入登陆servlet!");
			String username = (String) req.getParameter("userName");
			String password = (String) req.getParameter("passWord");
			if (!userService.login(username, password)) {
				errors.put("p_message",
						"<script type='text/javascript'>alert('用户名或者密码错误！')</script>");
				req.setAttribute("errors", errors);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("loginName", username);
				resp.sendRedirect("book_getAll.do?flag=book_getAll&pageNum=1");
			}
		}else if("user_unlogin".equals(flag)){
			System.out.println("用户退出登录!");
			HttpSession session = req.getSession();
			session.removeAttribute("loginName");
			resp.sendRedirect("login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
