package com.maera;

public final class Airport {
    private String _name;
    private String _icaoCode;
    private Boolean _hasMetar;
    private Boolean _hasTaf;
    private FIR _fir;

    public Airport(String name, String icaoCode, FIR fir, Boolean metar, Boolean taf){
        _name = name;
        _icaoCode = icaoCode;
        _fir = fir;
        _hasMetar = metar;
        _hasTaf = taf;
    }

    public String getAirportName(){ return _name; }

    public String getIcaoCode(){ return _icaoCode; }

    public FIR getFIR(){ return _fir; }

    public Boolean hasMetar(){ return _hasMetar; }

    public Boolean hasTaf(){ return _hasTaf; }

    public void setAirportName(String name){ _name = name; }

    public void setIcaoCode(String icaoCode){ _icaoCode = icaoCode; }

}
