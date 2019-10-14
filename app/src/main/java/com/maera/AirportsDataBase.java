package com.maera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class AirportsDataBase {
    static final private Airport SABE = new Airport("Aeroparque",      "SABE",true);
    static final private Airport SAZA = new Airport("Azul",            "SAZA", false);
    static final private Airport SAZB = new Airport("Bahía Blanca",    "SAZB",true);
    static final private Airport SAZS = new Airport("Bariloche",       "SAZS",true);

    static final private Airport SADO = new Airport("Campo de Mayo",   "SADO", false);
    static final private Airport SAZY = new Airport("Chapelco",        "SAZY",true);
    static final private Airport SAAC = new Airport("Concordia",       "SAAC",false);
    static final private Airport SADP = new Airport("El Palomar",      "SADP",true);

    static final private Airport SAEZ = new Airport("Ezeiza",          "SAEZ",true);
    static final private Airport SAZG = new Airport("General Pico",    "SAZG",false);
    static final private Airport SAAG = new Airport("Gualeguaychú",    "SAAG", false);
    static final private Airport SAAJ = new Airport("Junín",           "SAZY",false);

    static final private Airport SAZM = new Airport("Mar del Plata",   "SAZM",true);
    static final private Airport SADJ = new Airport("Mariano Moreno",  "SADJ",false);
    static final private Airport SADM = new Airport("Marón",           "SADM",false);
    static final private Airport SAZN = new Airport("Neuquén",         "SAZN",true);

    static final private Airport SAAP = new Airport("Paraná",          "SAAP",true);
    static final private Airport SAAR = new Airport("Rosario",         "SAAR",true);
    static final private Airport SADF = new Airport("San Fernando",    "SADF",true);
    static final private Airport SAZR = new Airport("Santa Rosa",      "SAZR",true);
    static final private Airport SAAV = new Airport("Sauce Viejo",     "SAAV",true);

    static final private FIR EZE = new FIR(
            "EZE",
            "Ezeiza",
            new ArrayList<>(
                    Arrays.asList(SABE, SAZA, SADO, SAAC, SAZG, SAAJ, SADJ, SADM, SAAG, SAZB, SAZS, SAZY, SADP, SAEZ, SAZM, SAZN, SAAP, SAAR, SADF, SAZR, SAAV )
            )
    );


    static final private Airport SANC = new Airport("Catamarca",            "SANC",true);
    static final private Airport SACO = new Airport("Córdoba",              "SACO",true);
    static final private Airport SACE = new Airport("Escuela de Aviación",  "SACE",false);
    static final private Airport SASJ = new Airport("Jujuy",                "SASJ",true);

    static final private Airport SANL = new Airport("La Rioja",             "SANL",true);
    static final private Airport SAOC = new Airport("Río Cuarto",           "SAOC",true);
    static final private Airport SASA = new Airport("Salta",                "SASA",true);
    static final private Airport SAOS = new Airport("Santa Rosa del Conlara","SAOS",false);

    static final private Airport SANE = new Airport("Santiago del Estero",  "SANE",true);
    static final private Airport SAST = new Airport("Tartagal",            "SAST",false);
    static final private Airport SANR = new Airport("Termas de Río Hondo",  "SANR",true);
    static final private Airport SANT = new Airport("Tucuman",              "SANT",true);

    static final private FIR CBA = new FIR(
            "CBA",
            "Córdoba",
            new ArrayList<>(
                Arrays.asList(SANC, SACO, SACE, SASJ, SANL, SAOC, SASA, SAOS, SANR, SANE, SAST, SANT)
            )
    );

    static final private Airport SAMM = new Airport("Malagüe", "SAMM", false);
    static final private Airport SAME = new Airport("Mendoza",      "SAME", true);
    static final private Airport SANU = new Airport("San Juan",     "SANU", true);
    static final private Airport SAOU = new Airport("San Luis",     "SAOU", true);

    static final private Airport SAMR = new Airport("San Rafael",   "SAMR", true);
    static final private Airport SAOR = new Airport("Villa Reynolds",   "SAOR", false);

    static final private FIR DOZ = new FIR(
            "DOZ",
            "Mendoza",
            new ArrayList<>(
                Arrays.asList(SAMM, SAME, SANU, SAOU, SAMR, SAOR)
            )
    );


    static final private Airport SAVC = new Airport("Comodoro Rivadavia", "SAVC", true);
    static final private Airport SAWC = new Airport("El Calafate",        "SAWC", true);
    static final private Airport SAVE = new Airport("Esquel",             "SAVE", true);
    static final private Airport SAWP = new Airport("Perito Moreno",             "SAVE", false);

    static final private Airport SAWD = new Airport("Puerto Deseado",             "SAWD", false);
    static final private Airport SAVY = new Airport("Puerto Madryn",             "SAVY", false);
    static final private Airport SAWG = new Airport("Río Gallegos",       "SAWG", true);
    static final private Airport SAWE = new Airport("Río Grande",         "SAWE", true);

    static final private Airport SAWJ = new Airport("San Julián",             "SAWJ", false);
    static final private Airport SAWU = new Airport("Santa Cruz",             "SAWU", false);
    static final private Airport SAVT = new Airport("Trelew",             "SAVT", true);
    static final private Airport SAWH = new Airport("Ushuaia",            "SAWH", true);
    static final private Airport SAVV = new Airport("Viedma",             "SAVV", true);
    static final private FIR CRV = new FIR(
            "CRV",
            "Comodoro Rivadavia",
            new ArrayList<>(
                Arrays.asList(SAVC, SAWC, SAVE, SAWP, SAWD, SAVY, SAWG, SAWE, SAWJ, SAWU, SAVT, SAWH, SAVV)
            )
    );

    static final private Airport SARC = new Airport("Corrientes",  "SARC",true);
    static final private Airport SARF = new Airport("Formosa",     "SARF",true);
    static final private Airport SARI = new Airport("Iguazú",      "SARI",true);
    static final private Airport SARL = new Airport("Paso de los Libres",      "SARL",false);

    static final private Airport SARP = new Airport("Posadas",     "SARP",true);
    static final private Airport SATR = new Airport("Reconquista",      "SATR",true);
    static final private Airport SARE = new Airport("Resistencia", "SARE",true);

    static final private FIR SIS = new FIR(
            "SIS",
            "Resistencia",
            new ArrayList<>(
                Arrays.asList(SARC, SARF, SARI, SARL, SARP, SATR, SARE)
            )
    );

    static final private Airport SAWB = new Airport("Marambio", "SAWB", true);

    static final private FIR ANTARTIDA = new FIR(
            "Antártida",
            "Antártida",
            new ArrayList<Airport>(){{add(SAWB);}}
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
            List<Airport> airports = fir.getAirports();
            for(Airport airport : airports )
                if( airport.getIcaoCode().equals(code))
                    return airport;
        }
        return null;
    }
}
