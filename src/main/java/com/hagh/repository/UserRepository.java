/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.repository;

import com.hagh.pojo.Users;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 84344
 */
public interface UserRepository {

    List<Users> getUser();

    List<Users> getUserByRole(String role);

    List<Users> getUserByAdmin(Map<String, String> params, int page);

    Users getUserById(int id);

    boolean addUser(Users user);

    boolean updateUser(Users user);

    boolean delUser(int id);

    Users getUserByUsername(String username);
    
    Users getUserByEmail(String email);


}
