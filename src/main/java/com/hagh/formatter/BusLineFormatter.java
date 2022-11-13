/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.BusLine;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author 84344
 */
public class BusLineFormatter implements Formatter<BusLine>{

    @Override
    public String print(BusLine t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public BusLine parse(String id, Locale locale) throws ParseException {
        BusLine bus = new BusLine();
        bus.setId(Integer.parseInt(id));
        return bus;
    }
    
}
