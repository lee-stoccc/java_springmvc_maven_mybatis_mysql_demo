package com.liaoze.stockmgt.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liaoze.stockmgt.dao.UserDao;
import com.liaoze.stockmgt.model.User;
import com.liaoze.stockmgt.service.UserService;

@Service  
public class UserServiceImpl implements UserService {

    @Autowired  
    private UserDao userDao;  

    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  
    }  
    
    public User selectUserByUserName(String userName) {
    	try {
    		User user = userDao.selectUserByUserName(userName);
    		return user;
    	}catch (Exception e) {
    		return null;
		}
	}

	@Override
	public int register(User user) {
	/*	int resCount;
		try {
			resCount = userDao.register(user);
			System.out.println(resCount);
			return resCount;
			
		}catch (Exception e) {
			resCount = -99;
			// TODO: handle exception
			return  resCount;
			
		}*/
		int register = userDao.register(user);
		
		return register;
	}

	@Override
	public int register(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.register(map);
	}

	@Override
	public int delUser(String userName) {
		return userDao.delUser(userName);
		
	}
}