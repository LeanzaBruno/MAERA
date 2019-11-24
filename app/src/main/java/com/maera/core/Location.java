package com.maera.core;

public final class Location {
    private String _city;
    private String _province;

    Location(String city, String province ){
        _city = city;
        _province = province;
    }

    public
    String getCity(){
        return _city;
    }

    public
    String getProvince(){ return _province; }

    public
    String toString(){
        return _city + ", " + _province;
    }
}
