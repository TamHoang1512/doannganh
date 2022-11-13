/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Users;
import com.hagh.repository.UserRepository;
import com.hagh.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 84344
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> getUser() {
        return this.userRepository.getUser();
    }

    @Override
    public Users getUserById(int id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public boolean addUser(Users user) {

        try {
            if (user.getUsername() != null) {
                return this.userRepository.addUser(user);
            }
            return false;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public List<Users> getUserByAdmin(Map<String, String> params, int page) {
        return this.userRepository.getUserByAdmin(params,page);
    }

    @Override
    public boolean delUser(int id) {
        return this.userRepository.delUser(id);
    }

    
    
    @Override
    public Users getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = this.getUserByUsername(username);
        if(u==null){
            throw new UsernameNotFoundException("Username not found");
        }
        else{
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRoleId().getRoleName()));
        
        
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
        }
    }

    @Override
    public boolean updateUser(Users user) {
        return this.userRepository.updateUser(user);
    }

    @Override
    public List<Users> getUserByRole(String role) {
        return this.userRepository.getUserByRole(role);
    }

    @Override
    public Users getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }

}
