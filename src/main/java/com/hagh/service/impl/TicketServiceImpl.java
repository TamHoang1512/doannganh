/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.BusTicket;
import com.hagh.repository.TicketRepository;
import com.hagh.service.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class TicketServiceImpl implements TicketService{
    
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public BusTicket getTicketById(int id) {
        return this.ticketRepository.getTicketById(id);
    }

    @Override
    public boolean bookTicket(BusTicket busTicket) {
        return this.ticketRepository.bookTicket(busTicket);
    }

    @Override
    public List<BusTicket> getAllTicketByOwn(int id) {
        return this.ticketRepository.getAllTicketByOwn(id);
    }

    @Override
    public boolean delTicket(int id) {
        return this.ticketRepository.delTicket(id);
    }
    
}
