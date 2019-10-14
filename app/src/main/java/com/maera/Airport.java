package com.maera;

final class Airport {
    private String _name;
    private String _icaoCode;
    private FIR.DESIGNATOR _fir;
    private Boolean _hasTaf;
    private WeatherReport _metar = null;
    private WeatherReport _taf = null;

    Airport(String name, String icaoCode, Boolean taf) {
        _name = name;
        _icaoCode = icaoCode;
        _hasTaf = taf;
    }

    String getAirportName() { return _name; }

    String getIcaoCode() { return _icaoCode; }

    Boolean hasTaf() { return _hasTaf; }

    void setAirportName(String name) { _name = name; }

    void setIcaoCode(String icaoCode) { _icaoCode = icaoCode; }

    void setMetar(WeatherReport metar) { _metar = metar; }

    void setTaf(WeatherReport taf){ _taf = taf; }
}
