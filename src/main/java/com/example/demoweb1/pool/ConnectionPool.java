package com.example.demoweb1.pool;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool connectionPool = new ConnectionPool();

    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private ConnectionPool(){
        String url="jdbc:postgresql://localhost:5432/infouser";
        Properties properties = new Properties();
        properties.put("user","postgres");
        properties.put("password","1404");
        for (int i = 0; i < 8; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url,properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            free.add(connection);
        }
    }
    public static ConnectionPool getInstance(){
        return connectionPool;
    }
    public Connection getConnection(){
        Connection connection =null;
        try {
            connection=free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void releaseConnection(Connection connection){
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
