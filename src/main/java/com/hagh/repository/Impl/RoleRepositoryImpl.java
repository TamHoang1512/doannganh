/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Role;
import com.hagh.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 84344
 */
@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository{
    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Role> getAdminRole() {
//        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
//        Query q = s.createQuery("From Role");
//        return q.getResultList();
        
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
//        CriteriaQuery<StationNews> qNews = b.createQuery(StationNews.class);
//        CriteriaQuery<Users> qUser = b.createQuery(Users.class);
        CriteriaQuery<Role> qRole = b.createQuery(Role.class);
        
        
//        Root<StationNews> rootNews = qNews.from(StationNews.class);
//        Root<Users> rootUser = qUser.from(Users.class);
        Root<Role> rootRole = qRole.from(Role.class);
        
        
//        Join<StationNews,Users> joinUser = rootNews.join("Users");
//        Join<Users,Role> joinRole = rootUser.join("Role");
                
        
//        qNews.select(rootNews);
        qRole.select(rootRole);
        Predicate pRole = b.like(rootRole.get("roleName").as(String.class), "admin");
//        Predicate p = b.equal(rootNews.get("userId").as(Integer.class), "1");
//        Predicate f = b.equal(rootNews.get("userId").as(Integer.class), "2");
        qRole.where(pRole);
        Query query = session.createQuery(qRole);
        return query.getResultList();
    }
    
    
    @Override
    public List<Role> getRolesRegister() {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Role> q = b.createQuery(Role.class);
        
        Root root = q.from(Role.class);
        q.select(root);
        
        
        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = b.greaterThan(root.get("roleId").as(String.class), "1");
        predicates.add(p1);
        q = q.where(predicates.toArray(new Predicate[]{}));
        
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public Role getRolesById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(Role.class,id);
    }

    
    @Override
    public List<Role> getRoles(){
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Role> qRole = b.createQuery(Role.class);
        
        Root<Role> rootRole = qRole.from(Role.class);
        qRole.select(rootRole);
        Predicate pRole = b.notLike(rootRole.get("roleName").as(String.class), "staff");
        qRole.where(pRole);
        Query query = session.createQuery(qRole);
        return query.getResultList();
    }

    public List<Role> getRolebyAdmin() {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Role> qRole = b.createQuery(Role.class);
        
        Root<Role> rootRole = qRole.from(Role.class);
        qRole.select(rootRole);
        Predicate pRole = b.notLike(rootRole.get("roleName").as(String.class), "staff");
        qRole.where(pRole);
        Query query = session.createQuery(qRole);
        return query.getResultList();
    }
}
