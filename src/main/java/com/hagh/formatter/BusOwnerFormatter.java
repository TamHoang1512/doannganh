/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.BusOwner;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author 84344
 */
public class BusOwnerFormatter implements Formatter<BusOwner>{

    @Override
    public String print(BusOwner t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public BusOwner parse(String id, Locale locale) throws ParseException {
        BusOwner bus = new BusOwner();
        bus.setId(Integer.parseInt(id));
        return bus;
    }
    
}
