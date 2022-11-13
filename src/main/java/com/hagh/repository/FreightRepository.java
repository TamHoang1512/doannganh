/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hagh.repository;

import com.hagh.pojo.Freight;
import java.util.List;

/**
 *
 * @author 84344
 */
public interface FreightRepository {

    List<Freight> getFreightByOwn(int ownId);

    boolean addFreightByOwn(Freight freight);
    
    boolean upDateFreightByOwn(Freight freight);

    boolean delFreightByOwn(int frId);

    public Freight getFreightById(int frId);

    public boolean sendFreight(int frId);

}
