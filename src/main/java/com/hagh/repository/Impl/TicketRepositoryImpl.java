/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.BusTicket;
import com.hagh.repository.TicketRepository;
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
public class TicketRepositoryImpl implements TicketRepository{

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;
    @Override
    public BusTicket getTicketById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(BusTicket.class, id);
    }

    @Override
    public boolean bookTicket(BusTicket busTicket) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(busTicket);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<BusTicket> getAllTicketByOwn(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusTicket.class);
        Root<BusTicket> root = q.from(BusTicket.class);
        q.select(root);
        Predicate p = b.equal(root.get("userId").get("userId").as(String.class), id);

        q.where(p);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean delTicket(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        try{
            BusTicket ticket = session.get(BusTicket.class, id);
            session.delete(ticket);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
}
