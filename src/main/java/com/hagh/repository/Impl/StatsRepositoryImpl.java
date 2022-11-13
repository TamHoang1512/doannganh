/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusTicket;
import com.hagh.repository.StatsRepository;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<Object[]> countBusByLine(int ownId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rB = q.from(Bus.class);
        Root rL = q.from(BusLine.class);

        Predicate pB = b.equal(rB.get("busLineId").get("id"), rL.get("id"));
        Predicate pL = b.equal(rL.get("busOwnerId").get("id"), ownId);
        Predicate pRs = b.and(pB, pL);
        q.where(pRs);
        q.multiselect(rL.get("id"), rL.get("fromPos").get("name"), rL.get("toPos").get("name"),
                b.count(rB.get("id")));
        q.groupBy(rL.get("id"));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> revenueStats(int quarter, int year,int ownId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);


        
//        Root rOwn = q.from(BusOwner.class);
        Root rLine = q.from(BusLine.class);
        Root rTic = q.from(BusTicket.class);
        Root rBus = q.from(Bus.class);
        
        
        q.where(
                b.equal(rLine.get("busOwnerId"), ownId),
                b.equal(rBus.get("busLineId"), rLine.get("id")),
                b.equal(rTic.get("busId"), rBus.get("id")),
                b.equal(b.function("QUARTER", Integer.class, rBus.get("startAt")), quarter),
                b.equal(b.function("YEAR", Integer.class, rBus.get("startAt")), year)
                
        );

        q.multiselect(
                rLine.get("fromPos").get("name"),
                rLine.get("toPos").get("name"),
                b.count(rBus.get("id")),
                b.sum(b.prod(rTic.get("numberOfSit"), rLine.get("ticketPrice")))
        );
        q.groupBy(rLine.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
        
        

    }

}
