package com.maera;

import java.util.ArrayList;
import java.util.List;

public final class AirportsDataBase {
    private static AirportsDataBase _INSTANCE = null;
    private static ArrayList<Airport> _airports = new ArrayList<>();


    private AirportsDataBase(){
        //Fir EZE
        _airports.add( new Airport("Aeroparque", "SABE", FIR.EZE, true, true));
        _airports.add( new Airport( "Bahía Blanca", "SAZB", FIR.EZE, true, true));
        _airports.add( new Airport( "Bariloche", "SAZS", FIR.EZE, true, true));
        _airports.add( new Airport( "Chapelco", "SAZY", FIR.EZE, true, true));
        _airports.add( new Airport( "El Palomar", "SADP", FIR.EZE, true, true));
        _airports.add( new Airport( "Ezeiza", "SAEZ", FIR.EZE, true, true));
        _airports.add( new Airport( "Mar del Plata", "SAZM", FIR.EZE, true, true));
        _airports.add( new Airport( "Neuquén", "SAZN", FIR.EZE, true, true));
        _airports.add( new Airport( "Paraná", "SAAP", FIR.EZE, true, true));
        _airports.add( new Airport( "Rosario", "SAAR", FIR.EZE, true, true));
        _airports.add( new Airport( "San Fernando", "SADP", FIR.EZE, true, true));
        _airports.add( new Airport( "Santa Rosa", "SAZR", FIR.EZE, true, true));
        _airports.add( new Airport( "Sauce Viejo", "SAAV", FIR.EZE, true, true));

        //FIR COR
        _airports.add( new Airport("Catamarca", "SANC", FIR.CBA, true, true ));
        _airports.add( new Airport("Córdoba", "SACO", FIR.CBA, true, true ));
        _airports.add( new Airport("Jujuy", "SASJ", FIR.CBA, true, true ));
        _airports.add( new Airport("La Rioja", "SANL", FIR.CBA, true, true ));
        _airports.add( new Airport("Río Cuarto", "SAOC", FIR.CBA, true, true ));
        _airports.add( new Airport("Salta", "SASA", FIR.CBA, true, true ));
        _airports.add( new Airport("Santiago del Estero", "SANE", FIR.CBA, true, true ));
        _airports.add( new Airport("Termas de Río Hondo", "SANR", FIR.CBA, true, true ));
        _airports.add( new Airport("Tucuman", "SANT", FIR.CBA, true, true ));

        //FIR DOZ
        _airports.add( new Airport("Mendoza", "SAME", FIR.DOZ, true, true));
        _airports.add( new Airport("San Juan", "SANU", FIR.DOZ, true, true));
        _airports.add( new Airport("San Luis", "SAOU", FIR.DOZ, true, true));
        _airports.add( new Airport("San Rafael", "SAMR", FIR.DOZ, true, true));

        //FIR SIS
        _airports.add( new Airport("Corrientes", "SARC", FIR.SIS, true, true));
        _airports.add( new Airport("Formosa", "SARF", FIR.SIS, true, true));
        _airports.add( new Airport("Iguazú", "SARI", FIR.SIS, true, true));
        _airports.add( new Airport("Posadas", "SARP", FIR.SIS, true, true));
        _airports.add( new Airport("Resistencia", "SARE", FIR.SIS, true, true));

        //FIR CVR
        _airports.add( new Airport("Comodoro Rivadavia", "SAVC", FIR.CRV, true, true));
        _airports.add( new Airport("El Calafate", "SAWC", FIR.CRV, true, true));
        _airports.add( new Airport("Esquel", "SAVE", FIR.CRV, true, true));
        _airports.add( new Airport("Río Gallegos", "SAWG", FIR.CRV, true, true));
        _airports.add( new Airport("Río Grande", "SAWE", FIR.CRV, true, true));
        _airports.add( new Airport("Trelew", "SAVT", FIR.CRV, true, true));
        _airports.add( new Airport("Ushuaia", "SAWH", FIR.CRV, true, true));
        _airports.add( new Airport("Viedma", "SAVV", FIR.CRV, true, true));
    }


    static AirportsDataBase getInstance(){
        if( _INSTANCE == null )
            _INSTANCE = new AirportsDataBase();
        return _INSTANCE;
    }

    Airport getAirport(int pos){ return _airports.get(pos); }


    /**
     *
     * @param fir La FIR asosiada con los aeropuertos
     * @return retorna una lista de aeropuertos que se encuentran de la FIR fir
     */
    List<Airport> getAirportsByFir(FIR fir){
        List<Airport> list = new ArrayList<>();
        for( Airport airport : _airports )
            if( airport.getFIR() == fir && airport.hasMetar() ) list.add(airport);
        return list;
    }

    /**
     *
     * @param fir La FIR asociada con los aeropuertos
     * @return Retorna una lista de aeropuertos que poseen TAF dentro de la FIR fir
     */
    ArrayList<Airport> getAirportsByTaf(FIR fir){
        ArrayList<Airport> list = new ArrayList<>();
        for( Airport airport : _airports )
            if( airport.getFIR() == fir && airport.hasTaf() ) list.add(airport);
        return list;
    }

    static public int getAirportsCount(){ return _airports.size(); }
}
