/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.repository.StatsRepository;
import com.hagh.service.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class StatsServiceImpl implements StatsService{
    
    @Autowired
    StatsRepository statsRepository;

    @Override
    public List<Object[]> countBusByLine(int ownId) {
        return this.statsRepository.countBusByLine(ownId);
    }

    @Override
    public List<Object[]> revenueStats(int quarter, int y, int ownId) {
        return this.statsRepository.revenueStats(quarter, y, ownId);
    }
    
}
