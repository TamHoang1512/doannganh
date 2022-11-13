/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.formatter;

import com.hagh.pojo.StationNews;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


/**
 *
 * @author 84344
 */
public class NewsFormatter implements Formatter<StationNews>{

    @Override
    public String print(StationNews t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public StationNews parse(String id, Locale locale) throws ParseException {
        StationNews s = new StationNews();
        s.setId(Integer.parseInt(id));
        return s;
    }
    
}
