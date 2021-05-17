/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbs.util;
import java.util.Date;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author ktepin
 */
public class DateJsonAdapter implements JsonbAdapter<Date, String> {

    @Override
    public String adaptToJson(Date orgn){
        Long l_date = orgn.getTime() / 1000;
        return Long.toString(l_date);
    }

    @Override
    public Date adaptFromJson(String adptd) throws NumberFormatException {
        long l_date;
        try{
            l_date = Long.parseLong(adptd);
        }
        catch(NumberFormatException e){
            throw e;
        }
        return new Date(l_date*1000);
    }
    
}
