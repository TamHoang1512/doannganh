/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.BusStation;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author 84344
 */
public class StationFormatter implements Formatter<BusStation>{

    @Override
    public String print(BusStation t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public BusStation parse(String id, Locale locale) throws ParseException {
        BusStation bus = new BusStation();
        bus.setId(Integer.parseInt(id));
        return bus;
    }
    
}
