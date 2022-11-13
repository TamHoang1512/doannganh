/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.validator;

import com.hagh.pojo.Users;
import com.hagh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author 84344
 */
//@Component
//@Service
public class RegisterUsersValidator implements Validator  {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users u = (Users) target;
        if (u.getEmail()==null) {
            errors.rejectValue("email",
                    "message.validate.user.nullStr");
        }
        
        if (u.getUsername().trim().length() < 4 || u.getUsername().trim().length() > 24) {
            errors.rejectValue("username",
                    "message.validate.user.username.size");
        }
        
        if (u.getPassword().trim().length() < 8 || u.getPassword().trim().length() > 24) {
            errors.rejectValue("password",
                    "message.validate.user.password.size");
        }
        Users validUsername = userService.getUserByUsername(u.getUsername().trim());
        if (validUsername != null) {
            errors.rejectValue("username",
                    "message.validate.user.username");
        }
        Users validEmail = userService.getUserByEmail(u.getEmail().trim());
        if (validEmail != null) {
            errors.rejectValue("email",
                    "message.validate.user.email");
        }
        if (!u.getPassword().trim()
                .equals(u.getConfirmPassword().trim())) {
            errors.rejectValue("password",
                    "message.validate.user.password");
        }
    }
}
