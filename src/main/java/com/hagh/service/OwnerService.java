/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.Rating;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface OwnerService {

    boolean checkBlockOrUnActive(int busOwnerId);
    
    List<BusOwner> getBusOwner();

    List<BusOwner> getBusOwnerByLead(int id);

    List<Bus> getBusByLine(int lineId);

    List<BusLine> getListBusLineByOwner(int id);
    
    List<Bus> getBusStartedByLine(int id);

    List<Bus> getListAllBusByOwner(int uId);

    boolean checkBusOwnerByUserId(int userId, int ownerId);

    boolean checkBusLineByOwnerId(int ownerId, int lineId);

    public boolean checkBusByUserId(int uId, int busId);

    BusOwner getBusOwnerByUserId(int id);

    BusOwner getBusOwnerById(int id);
    
    List<Rating> getRateByOwnId(int ownId);
    boolean addComment(Rating rate);
    boolean checkCommented(int ownId,int userId);
}
