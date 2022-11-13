/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.Rating;
import com.hagh.repository.OwnerRepository;
import com.hagh.service.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class OwnerServiceImpl implements OwnerService{
    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public List<BusOwner> getBusOwnerByLead(int id) {
        return this.ownerRepository.getBusOwnerByLead(id);
    }

    @Override
    public List<BusLine> getListBusLineByOwner(int id) {
        return this.ownerRepository.getListBusLineByOwner(id);
    }

    @Override
    public boolean checkBusOwnerByUserId(int userId, int ownerId) {
        return this.ownerRepository.checkBusOwnerByUserId(userId, ownerId);
    }

    @Override
    public List<Bus> getBusByLine(int lineId) {
        return this.ownerRepository.getBusByLine(lineId);
    }

    @Override
    public boolean checkBusLineByOwnerId(int ownerId, int lineId) {
        return this.ownerRepository.checkBusLineByOwnerId(ownerId, lineId);
    }

    @Override
    public List<Bus> getListAllBusByOwner(int uId) {
        return this.ownerRepository.getListAllBusByOwner(uId);
    }

    @Override
    public boolean checkBusByUserId(int uId, int busId) {
        return this.ownerRepository.checkBusByUserId(uId, busId);
    }

    @Override
    public boolean checkBlockOrUnActive(int busOwnerId) {
        return this.ownerRepository.checkBlockOrUnActive(busOwnerId);
    }

    @Override
    public BusOwner getBusOwnerByUserId(int id) {
        return this.ownerRepository.getBusOwnerByUserId(id);
    }

    @Override
    public BusOwner getBusOwnerById(int id) {
        return this.ownerRepository.getBusOwnerById(id);
    }

    @Override
    public List<BusOwner> getBusOwner() {
        return this.ownerRepository.getBusOwner();
    }

    @Override
    public List<Rating> getRateByOwnId(int ownId) {
        return this.ownerRepository.getRateByOwnId(ownId);
    }

    @Override
    public boolean addComment(Rating rate) {
        return this.ownerRepository.addComment(rate);
    }

    @Override
    public boolean checkCommented(int ownId, int userId) {
        return this.ownerRepository.checkCommented(ownId, userId);
    }

    @Override
    public List<Bus> getBusStartedByLine(int id) {
        return this.ownerRepository.getBusStartedByLine(id);
    }
    
}
