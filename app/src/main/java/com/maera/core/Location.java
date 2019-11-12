package com.maera.core;

public final class Location {
    private String _city;
    private PROVINCE _province;

    Location(String city, PROVINCE province ){
        _city = city;
        _province = province;
    }

    public
    String getCity(){
        return _city;
    }

    public
    PROVINCE getProvince(){ return _province; }

    public
    String toString(){
        return _city + ", " + _province.toString();
    }
}
