/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusStation;
import com.hagh.repository.StationRepository;
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
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<BusStation> getStation() {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery qNews = b.createQuery(BusStation.class);
        Root<BusStation> rootNews = qNews.from(BusStation.class);
        qNews.select(rootNews);
        Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "0");
        qNews.where(pBlock).orderBy(b.desc(rootNews.get("createdAt")));
        qNews.where(pBlock);
        Query query = session.createQuery(qNews);
        return query.getResultList();
    }

    @Override
    public BusStation getBusStationById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(BusStation.class, id);
    }

    @Override
    public List<BusLine> getBusLine() {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From BusLine");
        return q.getResultList();
    }

    @Override
    public boolean delStation(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusStation sta = s.get(BusStation.class, id);
            s.delete(sta);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean blockStation(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusStation sta = s.get(BusStation.class, id);
            if (sta.getIsBlock() == 0) {
                sta.setIsBlock(Short.parseShort("1"));
            } else {
                sta.setIsBlock(Short.parseShort("0"));
            }

            s.saveOrUpdate(sta);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean addStation(BusStation station) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(station);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<BusStation> getBlockStation() {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery qSta = b.createQuery(BusStation.class);
        Root<BusStation> rootNews = qSta.from(BusStation.class);
        qSta.select(rootNews);
        Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "1");
        qSta.where(pBlock).orderBy(b.desc(rootNews.get("createdAt")));
        qSta.where(pBlock);
        Query query = session.createQuery(qSta);

        return query.getResultList();
    }

    @Override
    public List<BusLine> getOwnerByStation(int stId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        
        
        
        
        
        CriteriaBuilder bL = session.getCriteriaBuilder();
        CriteriaQuery qL = bL.createQuery(BusLine.class);
        Root<BusLine> rootL = qL.from(BusLine.class);
        qL.select(rootL);
        
        Predicate pL = bL.equal(rootL.get("fromPos").get("id"), stId);
        qL.where(pL);
        

        Query query = session.createQuery(qL);

        return query.getResultList();
    }

}
