/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.service.impl;

import com.hagh.pojo.Freight;
import com.hagh.repository.FreightRepository;
import com.hagh.service.FreightService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 84344
 */
@Service
public class FreightServiceImpl implements FreightService{
    
    @Autowired
    FreightRepository freightRepository;

    @Override
    public List<Freight> getFreightByOwn(int ownId) {
        return this.freightRepository.getFreightByOwn(ownId);
    }

    @Override
    public boolean addFreightByOwn(Freight freight) {
        return this.freightRepository.addFreightByOwn(freight);
    }

    @Override
    public boolean delFreightByOwn(int frId) {
        return this.freightRepository.delFreightByOwn(frId);
    }

    @Override
    public Freight getFreightById(int frId) {
        return this.freightRepository.getFreightById(frId);
    }

    @Override
    public boolean sendFreight(int frId) {
        return this.freightRepository.sendFreight(frId);
    }

    @Override
    public boolean upDateFreightByOwn(Freight freight) {
        return this.freightRepository.upDateFreightByOwn(freight);
    }
    
}
