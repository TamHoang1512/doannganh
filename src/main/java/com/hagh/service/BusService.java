/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface BusService {

    List<Bus> getBus();

    Bus getBusById(int id);

    BusLine getBusLineById(int id);

    public boolean delBus(int id);

    public boolean addBus(Bus bus);

    public boolean updateBus(Bus bus);

    public boolean addBusLine(BusLine busline);

    public boolean updateBusLine(BusLine busline);

    public boolean delBusLine(int id);

    public int getEmptySitByBus(int id);

    List<BusOwner> getBusOwners(int Block);

    List<BusOwner> getBusOwnersActive(int active);

    public BusOwner getBusOwnerById(int id);

    public boolean addBusOwner(BusOwner owner);

    public boolean updateBusOwner(BusOwner owner);

    public boolean delBusOwner(int id);

    public boolean blockBusOwner(int id);

    public boolean activeBusOwner(int id);

    public List<Bus> getAllBusByOwner(int id);

    List<Bus> offerTrip(int busId);
    
    List<Bus> findBus(int fromPos, int toPos,int price);

}
