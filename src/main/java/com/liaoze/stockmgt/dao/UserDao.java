package com.liaoze.stockmgt.dao;

import java.util.Map;

import com.liaoze.stockmgt.model.User;

public interface UserDao {
    /**
     * @param userId
     * @return User
     */
    public User selectUserById(Integer userId);
    public User selectUserByUserName(String userName);
    public int  register(User user);
    public int  register(Map<String, Object> map);
    public int  delUser(String userName);
}