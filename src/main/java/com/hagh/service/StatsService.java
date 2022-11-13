/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.service;

import java.util.List;

/**
 *
 * @author 84344
 */
public interface StatsService {
    List<Object[]> countBusByLine(int ownId);
    List<Object[]> revenueStats(int quarter, int y,int ownId);
}
