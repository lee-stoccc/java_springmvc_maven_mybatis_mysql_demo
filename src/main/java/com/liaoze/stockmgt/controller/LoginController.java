package com.liaoze.stockmgt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liaoze.stockmgt.impl.UserServiceImpl;
import com.liaoze.stockmgt.model.User;
import com.liaoze.stockmgt.service.UserService;

@Controller
public class LoginController {

	private User user;
	@Resource
	private UserServiceImpl userServiceImpl;

	@Resource
	private UserService userService;

	/*
	 * 通过@responseBody 来获取参数，但返回的 String 是字符串，不是跳转的页面，也不是json
	 */

	/*
	 * @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String loginCheck(String userName, String password) {
	 * System.out.println(userName); User user =
	 * userServiceImpl.selectUserByUserName(userName); if (user != null) {
	 * System.out.println("账号密码正确"); return "login"; } else {
	 * System.out.println("没有这个账号密码"); return "unlogin"; } }
	 */

	/*
	 * 通过@HttpServletRequest 来获取参数，但返回的 String 是页面
	 */

	/*
	 * @RequestMapping(value = "/loginCheck", method = RequestMethod.POST) public
	 * String loginCheck(HttpServletRequest request) throws Exception { String
	 * userName = request.getParameter("userName"); String password =
	 * request.getParameter("password"); User user =
	 * userServiceImpl.selectUserByUserName(userName); if (user == null) { return
	 * "/login"; } if (user.getUserPassword().equals(password)) {
	 * System.out.println("账号密码正确"); return "redirect:/index"; } else {
	 * System.out.println("密码错误"); return "forward:/hello"; } }
	 */

	/*
	 * 通过@HttpServletRequest 来获取参数，但返回的 String 是页面
	 */
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName"); // 获取 ajax 参数
		String password = request.getParameter("password");
		User user = userServiceImpl.selectUserByUserName(userName); // 通过service接口实现层获取持久层数据，并且返回User对象实体
		Map<String, Object> map = new HashMap<String, Object>(); // 返回Json 用到
		if (user == null) {
			map.put("code", "0");
			map.put("url", null);
			return map;
		}
		if (user.getUserPassword().equals(password)) {
			HttpSession session = request.getSession(); // 开启session
			session.setAttribute("userName", user.getUserName()); // 把数据保存到session中
			session.setAttribute("password", user.getUserPassword());
			String sessionId = session.getId();
			session.setAttribute("sessionId", sessionId);
			if (session.isNew()) { // 判断是否有session
			} else {
			}

			System.out.println("账号密码正确");
			map.put("code", "1");
			map.put("url", "/stockmgt_maven_project/index");
		} else {
			System.out.println("密码错误");
			map.put("code", "2");
			map.put("url", "/stockmgt_maven_project/hello");
		}
		return map;

	}

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// 先判断账号是否穿在
		User user = userService.selectUserByUserName(userName);
		if (user == null) {
			/*
			 * User user2 = new User(); user2.setUserName(userName);
			 * user2.setUserPassword(password);
			 */
			Map<String, Object> Map = new HashMap<>();
			Map.put("userName", userName);
			Map.put("userPassword", password);
			int resCount = userService.register(Map);
			if (resCount == 1) {
				map.put("code", "1");
				map.put("msg", "注册成功");
				return map;
			} else {
				map.put("code", "0");
				map.put("msg", "注册失败");
				return map;
			}
		} else {
			map.put("msg", "此账号已存在");
			map.put("code", "-9");
			return map;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister() {
		return "/regist/register";
	}

	@RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
	public String registerSuccess() {
		return "/regist/registerSuccess";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/checkout")
	@ResponseBody
	public Map<String , Object> checkOut(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		session.removeAttribute("userName");
		session.removeAttribute("password");
		session.removeAttribute("sessionId");
		map.put("code", "1");
		return map;
	}
	
	
	
	@RequestMapping("/delUser")
	@ResponseBody
	public Map<String, Object> delUser(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String userName = request.getParameter("userName");
		int user = userServiceImpl.delUser(userName);
		if (user == 1) {
			map.put("code", "1");
			return map;
		}
		
		return null;
	}
}























