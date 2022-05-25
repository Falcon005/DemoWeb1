package com.example.demoweb1.dao;

public interface UserDao {
    boolean authenticate(String login,String password);
}
