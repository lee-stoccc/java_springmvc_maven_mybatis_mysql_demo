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
	 * ͨ��@responseBody ����ȡ�����������ص� String ���ַ�����������ת��ҳ�棬Ҳ����json
	 */

	/*
	 * @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String loginCheck(String userName, String password) {
	 * System.out.println(userName); User user =
	 * userServiceImpl.selectUserByUserName(userName); if (user != null) {
	 * System.out.println("�˺�������ȷ"); return "login"; } else {
	 * System.out.println("û������˺�����"); return "unlogin"; } }
	 */

	/*
	 * ͨ��@HttpServletRequest ����ȡ�����������ص� String ��ҳ��
	 */

	/*
	 * @RequestMapping(value = "/loginCheck", method = RequestMethod.POST) public
	 * String loginCheck(HttpServletRequest request) throws Exception { String
	 * userName = request.getParameter("userName"); String password =
	 * request.getParameter("password"); User user =
	 * userServiceImpl.selectUserByUserName(userName); if (user == null) { return
	 * "/login"; } if (user.getUserPassword().equals(password)) {
	 * System.out.println("�˺�������ȷ"); return "redirect:/index"; } else {
	 * System.out.println("�������"); return "forward:/hello"; } }
	 */

	/*
	 * ͨ��@HttpServletRequest ����ȡ�����������ص� String ��ҳ��
	 */
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName"); // ��ȡ ajax ����
		String password = request.getParameter("password");
		User user = userServiceImpl.selectUserByUserName(userName); // ͨ��service�ӿ�ʵ�ֲ��ȡ�־ò����ݣ����ҷ���User����ʵ��
		Map<String, Object> map = new HashMap<String, Object>(); // ����Json �õ�
		if (user == null) {
			map.put("code", "0");
			map.put("url", null);
			return map;
		}
		if (user.getUserPassword().equals(password)) {
			HttpSession session = request.getSession(); // ����session
			session.setAttribute("userName", user.getUserName()); // �����ݱ��浽session��
			session.setAttribute("password", user.getUserPassword());
			String sessionId = session.getId();
			session.setAttribute("sessionId", sessionId);
			if (session.isNew()) { // �ж��Ƿ���session
			} else {
			}

			System.out.println("�˺�������ȷ");
			map.put("code", "1");
			map.put("url", "/stockmgt_maven_project/index");
		} else {
			System.out.println("�������");
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
		// ���ж��˺��Ƿ���
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
				map.put("msg", "ע��ɹ�");
				return map;
			} else {
				map.put("code", "0");
				map.put("msg", "ע��ʧ��");
				return map;
			}
		} else {
			map.put("msg", "���˺��Ѵ���");
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























