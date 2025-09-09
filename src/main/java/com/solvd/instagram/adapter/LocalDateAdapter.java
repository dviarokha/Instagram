package com.solvd.instagram.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.DateTimeException;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String xmlData) throws Exception {
        if (xmlData == null || xmlData.isEmpty()) {
            throw new IllegalArgumentException("Empty or null data provided");
        }
        try {
            return LocalDate.parse(xmlData);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid format of data provided" + xmlData);
        }

    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        if (localDate == null) {
            throw new IllegalArgumentException("Invalid data provided");
        }
        return localDate.toString();
    }
}
