package com.example.demoweb1.service.impl;

import com.example.demoweb1.dao.impl.UserDaoImpl;
import com.example.demoweb1.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance=new UserServiceImpl();
    private UserServiceImpl(){

    }
    public static UserServiceImpl getInstance(){
        return instance;
    }
    @Override
    public boolean authenticate(String login, String password) {
        //validate login,pass + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match=userDao.authenticate(login,password);
        return match;
    }
}
