/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.repository;

import com.hagh.pojo.Role;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 84344
 */
public interface RoleRepository {
    List<Role> getRoles();
    List<Role> getAdminRole();
    List<Role> getRolesRegister();
    Role getRolesById(int id);
}
