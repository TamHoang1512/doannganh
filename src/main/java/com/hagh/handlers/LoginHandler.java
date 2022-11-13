package com.hagh.handlers;

import com.hagh.pojo.Users;
import com.hagh.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 84344
 */
@Component
public class LoginHandler implements AuthenticationSuccessHandler{
    @Autowired UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        Users u = this.userService.getUserByUsername(a.getName());
        request.getSession().setAttribute("currentUser", u);
        if(u.getRoleId().getRoleName().equals("admin")){
            response.sendRedirect("/admin");
        }
        else if(u.getRoleId().getRoleName().equals("bus_owner")){
            response.sendRedirect("/owner/"+u.getUserId().toString());
        }
        else response.sendRedirect("/");
    }
    
}
