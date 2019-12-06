package com.maera.core;

public final class Location {
    private String _locality;
    private String _province;

    Location(String city, String province ){
        _locality = city;
        _province = province;
    }

    public
    String getLocality(){
        return _locality;
    }

    public
    String getProvince(){ return _province; }

    public
    String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(_locality);
        if( _province == null || _province.isEmpty() )
            return builder.toString();
        return builder.append(", ").append(_province).toString();
    }
}
