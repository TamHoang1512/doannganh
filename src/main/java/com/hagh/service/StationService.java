/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusStation;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface StationService {

    List<BusStation> getStation();

    BusStation getStationById(int id);

    List<BusLine> getBusLine();
    
    boolean delStation(int id);
    boolean blockStation(int id);
    boolean addStation(BusStation station);
    List<BusStation> getBlockStation();
    List<BusLine> getOwnerByStation(int stId);
}
