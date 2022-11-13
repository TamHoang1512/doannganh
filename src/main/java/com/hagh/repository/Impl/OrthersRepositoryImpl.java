/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.repository.Impl;

import com.hagh.pojo.StationNews;
import com.hagh.repository.OrthersRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 84344
 */
@Repository
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:databases.properties")
@Transactional
public class OrthersRepositoryImpl implements OrthersRepository {

    @Autowired
    private LocalSessionFactoryBean getSessionFactoryBean;

    @Override
    public List<StationNews> getNews(String role) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        if (role.equals("")) {

            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery qNews = b.createQuery(StationNews.class);
            Root<StationNews> rootNews = qNews.from(StationNews.class);
            qNews.select(rootNews);
            Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "0");
            qNews.where(pBlock).orderBy(b.desc(rootNews.get("createdAt")));
            qNews.where(pBlock);
            Query query = session.createQuery(qNews);

            return query.getResultList();

        } else {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery qNews = b.createQuery(StationNews.class);
            Root<StationNews> rootNews = qNews.from(StationNews.class);
            qNews.select(rootNews);
            Predicate pNews = b.equal(rootNews.get("userId").get("roleId").get("roleName").as(String.class), role);
            Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "0");
            qNews.where(pNews).orderBy(b.desc(rootNews.get("createdAt")));
            Predicate pRs = b.and(pNews, pBlock);
            qNews.where(b.and(pNews, pBlock));
            qNews.where(pRs);
            Query query = session.createQuery(qNews).setMaxResults(5);

            return query.getResultList();
        }
    }

    @Override
    public boolean addNews(StationNews news) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.save(news);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public StationNews getNewsById(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        return s.get(StationNews.class, id);
    }

    @Override
    public boolean delNews(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            StationNews news = s.get(StationNews.class, id);
            s.delete(news);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean blockNews(int id) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            StationNews news = s.get(StationNews.class, id);
            if (news.getIsBlock() == 0) {
                news.setIsBlock(Short.parseShort("1"));
            } else {
                news.setIsBlock(Short.parseShort("0"));
            }

            s.saveOrUpdate(news);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<StationNews> getBlockNews(String role) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();
        if (role.equals("")) {

            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery qNews = b.createQuery(StationNews.class);
            Root<StationNews> rootNews = qNews.from(StationNews.class);
            qNews.select(rootNews);
            Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "1");
            qNews.where(pBlock).orderBy(b.desc(rootNews.get("createdAt")));
            qNews.where(pBlock);
            Query query = session.createQuery(qNews);

            return query.getResultList();

        } else {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery qNews = b.createQuery(StationNews.class);
            Root<StationNews> rootNews = qNews.from(StationNews.class);
            qNews.select(rootNews);
            Predicate pNews = b.equal(rootNews.get("userId").get("roleId").get("roleName").as(String.class), role);
            Predicate pBlock = b.equal(rootNews.get("isBlock").as(String.class), "1");
            qNews.where(pNews).orderBy(b.desc(rootNews.get("createdAt")));
            Predicate pRs = b.and(pNews, pBlock);
            qNews.where(b.and(pNews, pBlock));
            qNews.where(pRs);
            Query query = session.createQuery(qNews).setMaxResults(5);

            return query.getResultList();
        }
    }

    @Override
    public List<StationNews> getNewsByOwner(int uId) {
        Session session = this.getSessionFactoryBean.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery qNews = b.createQuery(StationNews.class);
        Root<StationNews> rootNews = qNews.from(StationNews.class);
        qNews.select(rootNews);
        Predicate pBlock = b.equal(rootNews.get("userId").get("userId").as(String.class), uId);
        qNews.where(pBlock).orderBy(b.desc(rootNews.get("createdAt")));
        qNews.where(pBlock);
        Query query = session.createQuery(qNews);
        return query.getResultList();

    }

    @Override
    public boolean updateNews(StationNews news) {
        Session s = this.getSessionFactoryBean.getObject().getCurrentSession();
        try {
            s.update(news);
            return true;
        } catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

    }
}
