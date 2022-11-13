/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.repository.BusRepository;
import com.hagh.service.BusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class BusServiceImpl implements BusService{
    
    @Autowired
    BusRepository busRepository ;
    

    @Override
    public List<Bus> getBus() {
        return this.busRepository.getBus();
    }

    @Override
    public Bus getBusById(int id) {
        return this.busRepository.getBusById(id);
    }
    /*@Override
    public List<BusLine> getBusLine() {
        return this.stationRepository.getBusLine();
    }*/

    
    
    
    
    @Override
    public List<BusOwner> getBusOwners(int Block) {
        return this.busRepository.getBusOwners(Block);
    }

    @Override
    public List<BusOwner> getBusOwnersActive(int active) {
        return this.busRepository.getBusOwnersActive(active);
    }

    @Override
    public BusOwner getBusOwnerById(int id) {
        return this.busRepository.getBusOwnerById(id);
    }

    @Override
    public boolean addBusOwner(BusOwner owner) {
        return this.busRepository.addBusOwner(owner);
    }

    @Override
    public boolean delBusOwner(int id) {
        return this.busRepository.delBusOwner(id);
    }

    @Override
    public boolean blockBusOwner(int id) {
        return this.busRepository.blockBusOwner(id);
    }

    @Override
    public boolean activeBusOwner(int id) {
        return this.busRepository.activeBusOwner(id);
    }

    @Override
    public int getEmptySitByBus(int id) {
        return this.busRepository.getEmptySitByBus(id);
    }

    @Override
    public List<Bus> getAllBusByOwner(int id) {
        return this.busRepository.getAllBusByOwner(id);
        
    }

    @Override
    public boolean delBus(int id) {
        return this.busRepository.delBus(id);
    }

    @Override
    public boolean addBus(Bus bus) {
        return this.busRepository.addBus(bus);
    }

    @Override
    public boolean addBusLine(BusLine busline) {
        return this.busRepository.addBusLine(busline);
    }

    @Override
    public boolean delBusLine(int id) {
        return this.busRepository.delBusLine(id);
    }

    @Override
    public BusLine getBusLineById(int id) {
        return this.busRepository.getBusLineById(id);
    }

    @Override
    public List<Bus> offerTrip(int busId) {
        return this.busRepository.offerTrip(busId);
    }

    @Override
    public boolean updateBus(Bus bus) {
        return this.busRepository.updateBus(bus);
    }

    @Override
    public boolean updateBusLine(BusLine busline) {
        return this.busRepository.updateBusLine(busline);
    }

    @Override
    public boolean updateBusOwner(BusOwner owner) {
        return this.busRepository.updateBusOwner(owner);
    }

    @Override
    public List<Bus> findBus(int fromPos, int toPos, int price) {
        return this.busRepository.findBus(fromPos, toPos, price);
    }
    
}
