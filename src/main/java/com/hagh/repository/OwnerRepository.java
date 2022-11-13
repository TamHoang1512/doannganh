/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.repository;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.Rating;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface OwnerRepository {

    boolean checkBlockOrUnActive(int busOwnerId);

    List<BusOwner> getBusOwnerByLead(int id);

    BusOwner getBusOwnerByUserId(int id);

    BusOwner getBusOwnerById(int id);

    List<BusOwner> getBusOwner();

    List<BusLine> getListBusLineByOwner(int id);
    
    List<Bus> getBusStartedByLine(int lineId);

    List<Bus> getListAllBusByOwner(int uId);

    List<Bus> getBusByLine(int lineId);

    boolean checkBusOwnerByUserId(int userId, int ownerId);

    boolean checkBusLineByOwnerId(int ownerId, int lineId);

    public boolean checkBusByUserId(int uId, int busId);

    List<Rating> getRateByOwnId(int ownId);

    boolean addComment(Rating rate);

    boolean checkCommented(int ownId, int userId);

}
