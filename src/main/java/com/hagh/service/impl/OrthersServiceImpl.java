/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.StationNews;
import com.hagh.repository.OrthersRepository;
import com.hagh.service.OrthersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class OrthersServiceImpl implements OrthersService {

    @Autowired
    OrthersRepository orthersRepository;

    @Override
    public List<StationNews> getNews(String Role) {
        return this.orthersRepository.getNews(Role);
    }

    @Override
    public boolean addNews(StationNews news) {

        try {
            this.orthersRepository.addNews(news);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public StationNews getNewsById(int id) {
        return this.orthersRepository.getNewsById(id);
    }

    @Override
    public boolean delNews(int id) {
        return this.orthersRepository.delNews(id);
    }

    @Override
    public boolean blockNews(int id) {
        return this.orthersRepository.blockNews(id);
    }

    @Override
    public List<StationNews> getBlockNews(String role) {
        return this.orthersRepository.getBlockNews(role);
    }

    @Override
    public List<StationNews> getNewsByOwner(int uId) {
        return this.orthersRepository.getNewsByOwner(uId);
    }

    @Override
    public boolean updateNews(StationNews news) {
        return this.orthersRepository.updateNews(news);
    }

}
