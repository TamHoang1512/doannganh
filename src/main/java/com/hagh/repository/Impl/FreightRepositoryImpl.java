/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Freight;
import com.hagh.repository.FreightRepository;
import java.util.List;
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
public class FreightRepositoryImpl  implements FreightRepository{
    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<Freight> getFreightByOwn(int ownId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Freight.class);
        Root<Freight> root = q.from(Freight.class);
        q.select(root);
        
        
        Predicate pF = b.equal(root.get("ownerId").get("id").as(String.class), ownId);


        q.where(pF).orderBy(b.desc(root.get("receiveDate")));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean addFreightByOwn(Freight freight) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(freight);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delFreightByOwn(int frId) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            Freight fr = s.get(Freight.class, frId);
            s.delete(fr);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Freight getFreightById(int frId) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(Freight.class, frId);
    }

    @Override
    public boolean sendFreight(int frId) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            Freight fr = s.get(Freight.class, frId);
            fr.setComplete(Short.parseShort("1"));
            s.saveOrUpdate(fr);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean upDateFreightByOwn(Freight freight) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.update(freight);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
}
