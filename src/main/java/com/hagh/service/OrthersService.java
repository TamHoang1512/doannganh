/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import com.hagh.pojo.StationNews;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface OrthersService {
    List<StationNews>getNews(String Role);
    List<StationNews> getBlockNews(String role);
    boolean addNews(StationNews news);
    boolean updateNews(StationNews news);
    StationNews getNewsById(int id);
    boolean delNews(int id);
    boolean blockNews(int id);
    
    List<StationNews>getNewsByOwner(int uId);
}
