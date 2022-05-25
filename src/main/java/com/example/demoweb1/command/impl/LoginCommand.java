package com.example.demoweb1.command.impl;

import com.example.demoweb1.command.Command;
import com.example.demoweb1.service.UserService;
import com.example.demoweb1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String login=request.getParameter("login");
        String password=request.getParameter("pass");
        UserService userService= UserServiceImpl.getInstance();
        boolean match=userService.authenticate(login,password);
        String page;
        if(match){
            request.setAttribute("user",login);
            page="pages/main.jsp";
        }
        else{
            request.setAttribute("login_msg","incorrect login or password");
            page="index.jsp";
        }
        return page;
    }
}
