/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;
import com.hagh.pojo.Bus;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
/**
 *
 * @author 84344
 */
public class BusFormatter implements Formatter<Bus>{

    @Override
    public String print(Bus t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Bus parse(String id, Locale locale) throws ParseException {
        Bus bus = new Bus();
        bus.setId(Integer.parseInt(id));
        return bus;
    }
    
}
