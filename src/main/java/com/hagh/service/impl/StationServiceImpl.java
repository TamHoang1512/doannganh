/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusStation;
import com.hagh.repository.StationRepository;
import com.hagh.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationRepository stationRepository;
    
    @Override
    public List<BusStation> getStation() {
        return this.stationRepository.getStation();
    }

    @Override
    public BusStation getStationById(int id) {
        return this.stationRepository.getBusStationById(id);
    }

    @Override
    public List<BusLine> getBusLine() {
        return this.stationRepository.getBusLine();
    }

    @Override
    public boolean delStation(int id) {
        return this.stationRepository.delStation(id);
    }

    @Override
    public boolean blockStation(int id) {
        return this.stationRepository.blockStation(id);
    }

    @Override
    public boolean addStation(BusStation station) {
        return this.stationRepository.addStation(station);
    }

    @Override
    public List<BusStation> getBlockStation() {
        return this.stationRepository.getBlockStation();
    }

    @Override
    public List<BusLine> getOwnerByStation(int stId) {
        return this.stationRepository.getOwnerByStation(stId);
    }
    
}
