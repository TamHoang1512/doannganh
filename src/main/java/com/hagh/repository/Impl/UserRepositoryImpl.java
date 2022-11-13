/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Users;
import com.hagh.repository.UserRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 84344
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    
    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;
    
    @Override
    public List<Users> getUser() {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From Users order by roleId");
        return q.getResultList();
    }

    @Override
    public Users getUserById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public boolean addUser(Users user) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try{
            s.save(user);
            return true;
       }catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Users> getUserByAdmin( Map<String, String> params, int page) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("username").as(String.class), String.format("%%%s%%", kw));
                Predicate p1 = b.like(root.get("email").as(String.class), String.format("%%%s%%", kw));
                Predicate pkq = b.or(p,p1);
                predicates.add(pkq);
            }


            String roleId = params.get("roleId");
            if (roleId != null) {
                Predicate p = b.equal(root.get("roleId"), Integer.parseInt(roleId));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }
        
        q.orderBy(b.asc(root.get("userId")));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean delUser(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try{
            Users user = s.get(Users.class, id);
            s.delete(user);
            return true;
       }catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Users getUserByUsername(String username) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);
        
        q.where(b.equal(root.get("username"), username));
        
        Query query = session.createQuery(q);
        return (Users) query.getSingleResult();
    }

    @Override
    public boolean updateUser(Users user) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(user);
            return true;
       }catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Users> getUserByRole(String role) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);
        
        q.where(b.equal(root.get("roleId").get("roleName"), role));
        
        Query query = session.createQuery(q);
        return  query.getResultList();
    }

    @Override
    public Users getUserByEmail(String email) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);
        
        q.where(b.equal(root.get("email"), email));
        
        Query query = session.createQuery(q);
        return (Users) query.getSingleResult();
    }

    
}
