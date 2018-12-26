package com.liaoze.stockmgt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.liaoze.stockmgt.impl.UserServiceImpl;
import com.liaoze.stockmgt.model.User;
import com.liaoze.stockmgt.service.UserService;

@Controller  
@SessionAttributes({"userName","sessionId","password"})
public class UserController {  

    @Resource  
    private UserServiceImpl UserServiceImpl; 

    /*
     * ʹ�� ModeAndView �����������ݷ���ҳ��
     */
    @RequestMapping("/")    
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("index"); 
        User user = UserServiceImpl.selectUserById(2); 
        mav.addObject("user", user);
        System.out.println("��վ�ѿ���");
        return mav; 
    }    
    
    
    
    /*
     * ͨ��@SessionAttributes ��������
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	String userName = (String) session.getAttribute("userName");    // ��������session��ȡ����
    	String password = (String) session.getAttribute("password");
    	String sessionId =(String) session.getId();
    	return "index";
    }
    
    @RequestMapping("/hello")
    public String hello() {
    	return "hello";
    }
}