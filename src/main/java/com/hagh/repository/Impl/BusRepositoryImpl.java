/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusTicket;
import com.hagh.repository.BusRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.velocity.runtime.directive.Foreach;
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
public class BusRepositoryImpl implements BusRepository {

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<Bus> getBus() {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From Bus order by startAt DESC");
        return q.getResultList();
    }

    @Override
    public Bus getBusById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(Bus.class, id);
    }

    @Override
    public List<BusOwner> getBusOwners(int block) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusOwner.class);
        Root<BusOwner> root = q.from(BusOwner.class);
        q.select(root);
        Predicate pBlock = b.equal(root.get("isBlock").as(String.class), block);
        Predicate pActive = b.equal(root.get("isActive").as(String.class), "1");
        Predicate pRs = b.and(pBlock, pActive);
        q.where(pRs);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<BusOwner> getBusOwnersActive(int active) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusOwner.class);
        Root<BusOwner> root = q.from(BusOwner.class);
        q.select(root);
        Predicate pBlock = b.equal(root.get("isActive").as(String.class), active);

        q.where(pBlock);
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean activeBusOwner(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusOwner owner = s.get(BusOwner.class, id);
            owner.setIsActive(Short.parseShort("1"));
            s.saveOrUpdate(owner);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public BusOwner getBusOwnerById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(BusOwner.class, id);
    }

    @Override
    public boolean addBusOwner(BusOwner owner) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(owner);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delBusOwner(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusOwner owner = s.get(BusOwner.class, id);
            s.delete(owner);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean blockBusOwner(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusOwner owner = s.get(BusOwner.class, id);
            if (owner.getIsBlock() == 0) {
                owner.setIsBlock(Short.parseShort("1"));
            } else {
                owner.setIsBlock(Short.parseShort("0"));
            }

            s.saveOrUpdate(owner);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public int getEmptySitByBus(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(BusTicket.class);
        Root<BusTicket> root = q.from(BusTicket.class);
        q.select(root);

        Predicate p = b.equal(root.get("busId").get("id").as(String.class), id);
        q.where(p);
        Query query = session.createQuery(q);

        int sum = 0;
        List<BusTicket> list = query.getResultList();
        if (!list.isEmpty()) {
            sum = list.stream().mapToInt(l -> l.getNumberOfSit()).sum();
        }
        Bus bus = session.get(Bus.class, id);
        return bus.getCapacitySit() - sum;
    }

    @Override
    public List<Bus> getAllBusByOwner(int id) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Bus.class);
        Root<Bus> root = q.from(Bus.class);
        q.select(root);

        Predicate p = b.equal(root.get("busLineId").get("busOwnerId").get("ownerUserId").get("userId").as(String.class), id);
        Predicate pOA = b.equal(root.get("busLineId").get("busOwnerId").get("isActive").as(String.class), 1);
        Predicate pOB = b.equal(root.get("busLineId").get("busOwnerId").get("isBlock").as(String.class), 0);
        q.where(b.and(p, pOA, pOB)).orderBy(b.desc(root.get("startAt")));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean delBus(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            Bus bus = s.get(Bus.class, id);
            s.delete(bus);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addBus(Bus bus) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(bus);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean addBusLine(BusLine busline) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(busline);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delBusLine(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            BusLine bus = s.get(BusLine.class, id);
            s.delete(bus);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public BusLine getBusLineById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(BusLine.class, id);
    }

    @Override
    public List<Bus> getAllBus(Map<String, String> params, int page) {

        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Bus> q = b.createQuery(Bus.class);
        Root root = q.from(Bus.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("username").as(String.class), String.format("%%%s%%", kw));
                Predicate p1 = b.like(root.get("email").as(String.class), String.format("%%%s%%", kw));
                Predicate pkq = b.or(p, p1);
                predicates.add(pkq);
            }

//            String fp = params.get("fromPrice");
//            if (fp != null) {
//                Predicate p = b.greaterThanOrEqualTo(root.get("price").as(Long.class), Long.parseLong(fp));
//                predicates.add(p);
//            }
//
//            String tp = params.get("toPrice");
//            if (tp != null) {
//                Predicate p = b.lessThanOrEqualTo(root.get("price").as(Long.class), Long.parseLong(tp));
//                predicates.add(p);
//            }
            String roleId = params.get("roleId");
            if (roleId != null) {
                Predicate p = b.equal(root.get("roleId"), Integer.parseInt(roleId));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.asc(root.get("userId")));

        Query query = session.createQuery(q);

        if (page > 0) {
            int size = 10;
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public List<Bus> offerTrip(int busId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();

        Bus getBus = session.get(Bus.class, busId);

        Date day = new Date();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Bus.class);
        Root<Bus> root = q.from(Bus.class);
        q.select(root);

        Predicate p1 = b.equal(root.get("busLineId").get("fromPos").get("id"), getBus.getBusLineId().getFromPos().getId());
        Predicate p2 = b.equal(root.get("busLineId").get("toPos").get("id"), getBus.getBusLineId().getToPos().getId());
        Predicate pDate = b.greaterThanOrEqualTo(root.get("startAt").as(Date.class), day);
        Predicate pRs = b.and(p1, p2, pDate);
        q.where(pRs);
        Query query = session.createQuery(q).setMaxResults(10);

        return query.getResultList();

    }

    @Override
    public boolean updateBus(Bus bus) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.update(bus);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBusLine(BusLine busline) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.update(busline);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBusOwner(BusOwner owner) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.update(owner);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Bus> findBus(int fromPos, int toPos, int price) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Bus> q = b.createQuery(Bus.class);

        Root rBus = q.from(Bus.class);
        try {
            if (fromPos != 0 && toPos != 0) {
                q.where(
                        b.equal(rBus.get("busLineId").get("fromPos"), fromPos),
                        b.equal(rBus.get("busLineId").get("toPos"), toPos),
                        b.greaterThanOrEqualTo(rBus.get("busLineId").get("ticketPrice"), price)
                );
            } else if (fromPos == 0 && toPos == 0) {
                q.where(
                        b.greaterThanOrEqualTo(rBus.get("busLineId").get("ticketPrice"), price)
                );
            } else if (fromPos != 0 && toPos == 0) {
                q.where(
                        b.equal(rBus.get("busLineId").get("fromPos"), fromPos),
                        b.greaterThanOrEqualTo(rBus.get("busLineId").get("ticketPrice"), price)
                );
            } else if (fromPos == 0 && toPos != 0) {
                q.where(
                        b.equal(rBus.get("busLineId").get("toPos"), toPos),
                        b.greaterThanOrEqualTo(rBus.get("busLineId").get("ticketPrice"), price)
                );
            }
        } catch (Exception ex) {
            q.where(
                    b.greaterThanOrEqualTo(rBus.get("busLineId").get("ticketPrice"), 0)
            );
        }

        Query query = session.createQuery(q);
        return query.getResultList();

    }

}
