/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.Rating;
import com.hagh.pojo.Users;
import com.hagh.repository.OwnerRepository;
import java.util.Date;
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
public class OwnerRepositoryImpl implements OwnerRepository {

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<BusOwner> getBusOwnerByLead(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusOwner.class);
        Root<BusOwner> root = q.from(BusOwner.class);
        q.select(root);
        Predicate pOwner = b.equal(root.get("ownerUserId").get("userId").as(String.class), id);
        Predicate pActive = b.equal(root.get("isActive").as(String.class), "1");
        Predicate pRs = b.and(pOwner, pActive);

        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<BusLine> getListBusLineByOwner(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusLine.class);
        Root<BusLine> root = q.from(BusLine.class);
        q.select(root);
        Predicate pLine = b.equal(root.get("busOwnerId").get("id").as(String.class), id);
        Predicate pActive = b.equal(root.get("busOwnerId").get("isActive").as(String.class), "1");
        Predicate pRs = b.and(pLine, pActive);

        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override//to check if own not in user -> get line list
    public boolean checkBusOwnerByUserId(int id, int ownerId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        Users user = session.get(Users.class, id);
        BusOwner own = session.get(BusOwner.class, ownerId);
        if (user == null || own == null) {
            return false;
        }
        if (own.getOwnerUserId().getUserId().equals(user.getUserId())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Bus> getBusByLine(int lineId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Bus.class);
        Root<Bus> root = q.from(Bus.class);
        q.select(root);
        Date day = new Date();
        Predicate pBus = b.equal(root.get("busLineId").get("id").as(String.class), lineId);
        Predicate pDate = b.greaterThanOrEqualTo(root.get("startAt").as(Date.class), day);
        Predicate pRs = b.and(pBus, pDate);

        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override//to check if line not belongs to own -> get bus list
    public boolean checkBusLineByOwnerId(int ownerId, int lineId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        BusOwner own = session.get(BusOwner.class, ownerId);
        BusLine line = session.get(BusLine.class, lineId);
        if (line == null || own == null) {
            return false;
        }
        if (line.getBusOwnerId().getId().equals(own.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Bus> getListAllBusByOwner(int uId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Bus.class);
        Root<Bus> root = q.from(Bus.class);
        q.select(root);
        Predicate pBus = b.equal(root.get("busLineId").get("busOwnerId").get("ownerUserId").get("userId").as(String.class), uId);
//        Predicate pActive = b.equal(root.get("isActive").as(String.class), "1");
//        Predicate pRs = b.and(pBlock,pActive);

        q.where(pBus).orderBy(b.desc(root.get("startAt")));
        Query query = session.createQuery(q).setMaxResults(10);

        return query.getResultList();
    }

    @Override//to check if line not belongs to own -> get bus list
    public boolean checkBusByUserId(int uId, int busId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        Users user = session.get(Users.class, uId);
        Bus bus = session.get(Bus.class, busId);
        if (user == null || bus == null) {
            return false;
        }
        if (bus.getBusLineId().getBusOwnerId().getOwnerUserId().getUserId().equals(user.getUserId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkBlockOrUnActive(int busOwnerId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusOwner own = session.get(BusOwner.class, busOwnerId);
            if(own==null)return false;
            if (own.getIsActive() == 0) {
                return false;
            }
            if (own.getIsBlock() == 1) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public BusOwner getBusOwnerByUserId(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusOwner.class);
        Root<BusOwner> root = q.from(BusOwner.class);
        q.select(root);
        Predicate pBus = b.equal(root.get("ownerUserId").get("userId").as(String.class), id);

        q.where(pBus);
        try{
            Query query = session.createQuery(q);
            return (BusOwner) query.getSingleResult();
        }catch(Exception ex){
            return null;
        }
        

        

    }

    @Override
    public BusOwner getBusOwnerById(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        return session.get(BusOwner.class, id);
    }

    @Override
    public List<BusOwner> getBusOwner() {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusOwner.class);
        Root<BusOwner> root = q.from(BusOwner.class);
        q.select(root);
        Predicate pBus = b.equal(root.get("isActive").as(String.class), 1);
        Predicate pBlock = b.equal(root.get("isBlock").as(String.class), 0);
        Predicate pRs = b.and(pBus, pBlock);

        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Rating> getRateByOwnId(int ownId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Rating.class);
        Root<Rating> root = q.from(Rating.class);
        q.select(root);
        Predicate p = b.equal(root.get("busOwnerId").get("id").as(String.class), ownId);
        q.where(p).orderBy(b.asc(root.get("id")));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean addComment(Rating rate) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(rate);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkCommented(int ownId, int userId) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        Users user = s.get(Users.class, userId);
        if (user.getRoleId().getRoleId() != 3) {
            return false;
        }
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Rating.class);
        Root<Rating> root = q.from(Rating.class);
        q.select(root);
        Predicate p2 = b.equal(root.get("userId").get("userId"), userId);
        Predicate p = b.equal(root.get("busOwnerId").get("id"), ownId).in(p2);

        Predicate pRs = b.equal(p2, p);
        q.where(p);
        Query query = s.createQuery(q);
        if (query != null) {
            return false;//dacomment
        }
        return true;
    }

    @Override
    public List<Bus> getBusStartedByLine(int lineId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Bus.class);
        Root<Bus> root = q.from(Bus.class);
        q.select(root);
        Date day = new Date();
        Predicate pBus = b.equal(root.get("busLineId").get("id").as(String.class), lineId);
        Predicate pDate = b.lessThanOrEqualTo(root.get("startAt").as(Date.class), day);
        Predicate pRs = b.and(pBus, pDate);

        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }
}
