/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.Role;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author 84344
 */
public class RoleFormatter implements Formatter<Role> {

    @Override
    public String print(Role r, Locale locale) {
        return String.valueOf(r.getRoleId());
    }

    @Override
    public Role parse(String id, Locale locale) throws ParseException {
        Role r = new Role();
        r.setRoleId(Integer.parseInt(id));
        return r;
    }
    
}
