/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.Users;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author 84344
 */
public class UsersFormatter implements Formatter<Users> {

    @Override
    public String print(Users t, Locale locale) {
        return String.valueOf(t.getUserId());
    }

    @Override
    public Users parse(String id, Locale locale) throws ParseException {
        Users u = new Users();
        u.setUserId(Integer.parseInt(id));
        return u;
    }
    
}
