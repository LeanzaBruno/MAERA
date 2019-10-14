package com.maera;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una fir
 */

class FIR {
    enum DESIGNATOR{EZE,CBA,SIS,DOZ,CRV,ANTARTIDA}
    private String            _designator;
    private String            _name;
    private List<Airport> _airports = null;


    FIR(String designator, String name){
        _designator = designator;
        _name = name;
    }


    FIR(String designator, String name, List<Airport> airports){
        _designator = designator;
        _name = name;
        _airports = airports;
    }

    String getFirCode(){ return _designator; }

    String getName(){ return _name; }

    void setAirportList(ArrayList<Airport> airportList){ _airports = airportList; }

    Airport getAirport(int position){
        return _airports.get(position);
    }

    List<Airport> getAirports(){ return _airports; }
}
