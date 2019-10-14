package com.maera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

final class AirportsDataBase {

    static final private Airport SABE = new Airport("Aeroparque",      "SABE",true);
    static final private Airport SAAG = new Airport("Gualeguaychú",    "SAAG", false);
    static final private Airport SAZB = new Airport("Bahía Blanca",    "SAZB",true);
    static final private Airport SAZS = new Airport("Bariloche",       "SAZS",true);
    static final private Airport SAZY = new Airport("Chapelco",        "SAZY",true);
    static final private Airport SADP = new Airport("El Palomar",      "SADP",true);
    static final private Airport SAEZ = new Airport("Ezeiza",          "SAEZ",true);
    static final private Airport SAZM = new Airport("Mar del Plata",   "SAZM",true);
    static final private Airport SAZN = new Airport("Neuquén",         "SAZN",true);
    static final private Airport SAAP = new Airport("Paraná",          "SAAP",true);
    static final private Airport SAAR = new Airport("Rosario",         "SAAR",true);
    static final private Airport SADF = new Airport("San Fernando",    "SADF",true);
    static final private Airport SAZR = new Airport("Santa Rosa",      "SAZR",true);
    static final private Airport SAAV = new Airport("Sauce Viejo",     "SAAV",true);

    static final FIR EZE = new FIR(
            "EZE",
            "Ezeiza",

            new LinkedList<Airport>(
                    Arrays.asList(SABE, SAAG, SAZB, SAZS, SAZY, SADP, SAEZ, SAZM, SAZN, SAAP, SAAR, SADF, SAZR, SAAV )
            )
    );

    static final FIR CBA = new FIR(
            "CBA",
            "Córdoba",
            new LinkedList<Airport>(
            Arrays.asList(
                    new Airport("Catamarca",            "SANC",true),
                    new Airport("La Rioja",             "SANL",true),
                    new Airport("Córdoba",              "SACO",true),
                    new Airport("Jujuy",                "SASJ",true),
                    new Airport("Río Cuarto",           "SAOC",true),
                    new Airport("Salta",                "SASA",true),
                    new Airport("Santiago del Estero",  "SANE",true),
                    new Airport("Termas de Río Hondo",  "SANR",true),
                    new Airport("Tucuman",              "SANT",true)
            )
            )
    );

    static final FIR DOZ = new FIR(
            "DOZ",
            "Mendoza",
            new LinkedList<Airport>(
            Arrays.asList(
                    new Airport("Mendoza",      "SAME", true),
                    new Airport("San Juan",     "SANU", true),
                    new Airport("San Luis",     "SAOU", true),
                    new Airport("San Rafael",   "SAMR", true)
            ))
    );

    static final FIR CRV = new FIR(
            "CRV",
            "Comodoro Rivadavia",
            new LinkedList<Airport>(
            Arrays.asList(
                    new Airport("Comodoro Rivadavia", "SAVC", true),
                    new Airport("El Calafate",        "SAWC", true),
                    new Airport("Esquel",             "SAVE", true),
                    new Airport("Río Gallegos",       "SAWG", true),
                    new Airport("Río Grande",         "SAWE", true),
                    new Airport("Trelew",             "SAVT", true),
                    new Airport("Ushuaia",            "SAWH", true),
                    new Airport("Viedma",             "SAVV", true)
            ))
    );

    static final FIR SIS = new FIR(
            "SIS",
            "Resistencia",
            new LinkedList<Airport>(
            Arrays.asList(
                    new Airport("Corrientes",  "SARC",true),
                    new Airport("Formosa",     "SARF",true),
                    new Airport("Iguazú",      "SARI",true),
                    new Airport("Posadas",     "SARP",true),
                    new Airport("Resistencia", "SARE",true)
            ))
    );

    static final FIR ANTARTIDA = new FIR(
            "Antártida",
            "Antártida",
            new LinkedList<Airport>(
                    Arrays.asList(
                    new Airport("Base Marambio", "SAWB", true)
                    )
            )
    );

    static ArrayList<FIR> getFIRList(){
        ArrayList<FIR> list = new ArrayList<>();
        list.add(EZE);
        list.add(CBA);
        list.add(DOZ);
        list.add(SIS);
        list.add(CRV);
        list.add(ANTARTIDA);
        return list;
    }

    static Airport getAirport(String code){
        final List<FIR> list = getFIRList();
        for(FIR fir : list ){
            List<Airport> airports = EZE.getAirports();
            for(Airport airport : airports )
                if( airport.getIcaoCode().equals(code))
                    return airport;
        }
        return null;
    }
}
