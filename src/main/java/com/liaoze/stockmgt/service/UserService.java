package com.liaoze.stockmgt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.liaoze.stockmgt.model.User;

@Service
public interface UserService {
    public User selectUserById(Integer userId);
    public User selectUserByUserName(String userName);
    public int register(User user);
    public int register(Map<String, Object> map);
    public int delUser(String userName);
    
}