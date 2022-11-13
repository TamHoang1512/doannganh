/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Role;
import com.hagh.repository.RoleRepository;
import com.hagh.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<Role> getAdminRole() {
        return this.roleRepository.getAdminRole();
    }

    @Override
    public List<Role> getRolesRegister() {
        return this.roleRepository.getRolesRegister();
    }

    @Override
    public Role getRolesById(int id) {
        return this.roleRepository.getRolesById(id);
    }

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.getRoles();
    }

    
}
