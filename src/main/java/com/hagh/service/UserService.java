/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import com.hagh.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author 84344
 */
public interface UserService extends UserDetailsService {

    List<Users> getUser();

    List<Users> getUserByAdmin(Map<String, String> params, int page);

    Users getUserById(int id);

    boolean addUser(Users user);

    boolean updateUser(Users user);

    boolean delUser(int id);

    List<Users> getUserByRole(String role);

    Users getUserByUsername(String username);
    
    Users getUserByEmail(String email);

    //validate

}
