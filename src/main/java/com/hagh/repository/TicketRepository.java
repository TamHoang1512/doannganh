/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.repository;

import com.hagh.pojo.BusTicket;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface TicketRepository {
    
    BusTicket getTicketById(int id);
    boolean bookTicket(BusTicket busTicket);
    
    List<BusTicket> getAllTicketByOwn(int id);
    boolean delTicket(int id);
}
