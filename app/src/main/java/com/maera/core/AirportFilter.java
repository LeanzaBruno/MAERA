package com.maera.core;

public class AirportFilter {
    public enum TYPE_OF_FILTER{ICAO, ANAC, LOCALITY, PROVINCE, NAME, EZE, CBA, DOZ, SIS, CRV, ANT, FAVS, NONE}
    private TYPE_OF_FILTER _search;

    public TYPE_OF_FILTER getType(){
        return _search;
    }

    public void setType(TYPE_OF_FILTER type){
        _search = type;
    }
}
