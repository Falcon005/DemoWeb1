package com.example.demoweb1.dao.impl;

import com.example.demoweb1.dao.BaseDao;
import com.example.demoweb1.dao.UserDao;
import com.example.demoweb1.entity.User;
import com.example.demoweb1.pool.ConnectionPool;
import com.example.demoweb1.service.impl.UserServiceImpl;
import org.intellij.lang.annotations.Language;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl(){

    }
    public static UserDaoImpl getInstance(){
        return instance;
    }
    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {


//        String username="postgres";
//        String pass="1404";

        boolean match=false;
        try(Connection connection= ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()){

            @Language("SQL")
            String sql="SELECT password FROM users WHERE name= '"+login+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            String passFromDb;
            if(resultSet.next()){
                passFromDb=resultSet.getString(1);
                match= password.equals(passFromDb);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return match;
    }
}
