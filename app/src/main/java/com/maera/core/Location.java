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
        StringBuilder builder = new StringBuilder();
        builder.append(_city);
        if( _province == null || _province.isEmpty() )
            return builder.toString();
        return builder.append(", ").append(_province).toString();
    }
}
