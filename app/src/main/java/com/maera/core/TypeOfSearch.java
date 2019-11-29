package com.maera.core;

public class TypeOfSearch {
    public enum TYPE_OF_SEARCH{ICAO, ANAC, LOCATION, NAME};
    private TYPE_OF_SEARCH _search;

    public TYPE_OF_SEARCH getType(){
        return _search;
    }

    public void setType(TYPE_OF_SEARCH type){
        _search = type;
    }
}
