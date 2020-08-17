package com.dxh.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
