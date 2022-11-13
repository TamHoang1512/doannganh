/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Bus;
import com.hagh.pojo.BusLine;
import com.hagh.pojo.BusOwner;
import com.hagh.pojo.BusTicket;
import com.hagh.pojo.Freight;
import com.hagh.pojo.StationNews;
import com.hagh.pojo.Users;
import com.hagh.repository.UserRepository;
import com.hagh.service.BusService;
import com.hagh.service.FreightService;
import com.hagh.service.OrthersService;
import com.hagh.service.OwnerService;
import com.hagh.service.TicketService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class AuthenticatedUserService {
    
    @Autowired
    FreightService freightService;
    
    @Autowired
    TicketService ticketService;
    
    @Autowired
    BusService busService;
    
    @Autowired
    OrthersService ortherService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OwnerService ownerService;
    
    public boolean hasId(int id){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.getUserByUsername(username);
        return user.getUserId().equals(id);
    }
   
    public boolean hasTrueBusOwnerId(int userId, int ownerId){
        return ownerService.checkBusOwnerByUserId(userId, ownerId);
    }
    public boolean hasBusLineId(int ownerId, int lineId){
        return ownerService.checkBusLineByOwnerId(ownerId, lineId);
    }
    public boolean checkBusByUserId(int uId, int busId){
        return ownerService.checkBusByUserId(uId, busId);
    }
    
    
    public boolean checkBusLineByUserId(int busLineId){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.getUserByUsername(username);
        BusLine line = busService.getBusLineById(busLineId);
        if(user==null || line == null)return false;
        if(line.getBusOwnerId().getOwnerUserId().getUserId().equals(user.getUserId()))return true;
        return false;
    }
    
    public boolean hasNewsId(int nid){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.getUserByUsername(username);
        StationNews news = ortherService.getNewsById(nid);
        if(news==null)return false;
        return user.getUserId().equals(news.getUserId().getUserId());
    }
    public boolean hasFreightId(int frId){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.getUserByUsername(username);
        BusOwner own =  ownerService.getBusOwnerByUserId(user.getUserId());
        Freight fr = freightService.getFreightById(frId);
        if(own==null||user==null||fr==null)return false;
        return   own.getId().equals(fr.getOwnerId().getId());
    }
    
    public boolean hasTicketId(int tid){
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.getUserByUsername(username);
        BusTicket ticket = ticketService.getTicketById(tid);
        if(ticket==null)return false;
        return user.getUserId().equals(ticket.getUserId().getUserId());
    }
}
