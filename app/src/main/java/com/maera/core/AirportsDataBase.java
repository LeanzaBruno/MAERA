package com.maera.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public
final
class AirportsDataBase {
    static final private Airport SABE = new Airport("Aeroparque Jorge Newbery", "SABE", "AEP", "EZE", "Ciudad Autonónoma de Buenos Aires", true);
    static final private Airport SAZA = new Airport("Azul", "SAZA", "ZUL", "EZE", "Buenos Aires", false);
    static final private Airport SAZB = new Airport("Comandante Espora", "SAZB", "", "EZE", "Bahía Blanca, Buenos Aires", true);
    static final private Airport SAZS = new Airport("San Carlos de Bariloche", "SAZS", "BAR", "EZE", "Bariloche, Río Negro", true);

    static final private Airport SADO = new Airport("Campo de Mayo", "SADO", "CPO", "EZE", "Buenos Aires", false);
    static final private Airport SAZY = new Airport("Aviador José Campos", "SAZY", "CHP", "EZE","San Martín de los Andes, Mendoza", true);
    static final private Airport SAAC = new Airport("Comodoro Pierrestegui","SAAC", "DIA", "EZE", "Concordia, Entre Ríos", false);
    static final private Airport SADP = new Airport("El Palomar", "SADP", "PAL", "EZE", "El Palomar, Buenos Aires", true);

    static final private Airport SAEZ = new Airport("Ministro Pistarini", "SAEZ", "EZE", "EZE", "Ezeiza, Buenos Aires", true);
    static final private Airport SAZG = new Airport("General Pico", "SAZG", "GPI", "EZE", "General Pico, La Pampa", false);
    static final private Airport SAAG = new Airport("Gualeguaychú", "SAAG", "GUA", "EZE", "Gualeguaychú, Entre Ríos", false);
    static final private Airport SAAJ = new Airport("Junín", "SAZY", "NIN", "EZE", "Junín, Buenos Aires", false);

    static final private Airport SAZM = new Airport("Astor Piazzolla", "SAZM", "MDP","EZE", "Mar del Plata, Buenos Aires", true);
    static final private Airport SADJ = new Airport("Mariano Moreno", "SADJ", "ENO", "EZE", "José C. Paz, Buenos Aires", false);
    static final private Airport SADM = new Airport("Presidente Rivadavia", "SADM", "MOR", "EZE", "Morón, Buenos Aires", false);
    static final private Airport SAZN = new Airport("Presidente Perón", "SAZN", "NEU", "EZE", "Neuquén, Neuquén", true);

    static final private Airport SAAP = new Airport("General Urquiza", "SAAP", "PAR","EZE", "Paraná, Entre Ríos", true);
    static final private Airport SAAR = new Airport("Islas Malvinas", "SAAR", "ROS", "EZE", "Rosario, Santa Fe", true);
    static final private Airport SADF = new Airport("San Fernando", "SADF", "FDO", "EZE", "San Fernando, Buenos Aires", true);
    static final private Airport SAZR = new Airport("Santa Rosa" , "SAZR", "OSA", "EZE", "Santa Rosa, La Pampa", true);
    static final private Airport SAAV = new Airport("Sauce Viejo", "SAAV", "SVO","EZE", "Sauce Viejo, Santa Fe", true);

    static final FIR EZE = new FIR(
            "EZE",
            "Ezeiza",
            new ArrayList<>(
                    Arrays.asList(SABE, SAZA, SADO, SAAC, SAZG, SAAJ, SADJ, SADM, SAAG, SAZB, SAZS, SAZY, SADP, SAEZ, SAZM, SAZN, SAAP, SAAR, SADF, SAZR, SAAV )
            )
    );


    static final private Airport SANC = new Airport("Catamarca",            "SANC", "CAT", "CBA", "San Fernando del Valle de Catamarca, Catamarca", true);
    static final private Airport SACO = new Airport("Ing. Taravella",              "SACO", "CBA", "CBA", "Córdoba, Córdoba", true);
    static final private Airport SACE = new Airport("Escuela de Aviación",  "SACE", "", "CBA", "Córdoba, Córdoba", false);
    static final private Airport SASJ = new Airport("Gobernador Guzmán",                "SASJ","JUJ", "CBA", "Jujuy, Jujuy", true);

    static final private Airport SANL = new Airport("Capitán Almonacid",             "SANL", "LAR", "CBA", "La Rioja, La Rioja", true);
    static final private Airport SAOC = new Airport("Área de Material",           "SAOC", "TRC", "CBA", "Río Cuarto, Córdoba", true);
    static final private Airport SASA = new Airport("General Miguel de Güemes",                "SASA", "", "CBA", "Salta, Salta", true);
    static final private Airport SAOS = new Airport("Santa Rosa del Conlara","SAOS", "SRC", "CBA", "Santa Rosa de Conlara, San Luis", false);

    static final private Airport SANE = new Airport("Vcom. Ángel La Paz Aragonés",  "SANE", "SDE", "CBA", "Santiago del Estero, Santiago del Estero", true);
    static final private Airport SAST = new Airport("General Mosconi",            "SAST", "TAR", "CBA", "Tartagal, Salta", false);
    static final private Airport SANR = new Airport("Termas de Río Hondo",  "SANR", "TRH", "CBA", "Termas de Río Hondo, Santiago del Estero", true);
    static final private Airport SANT = new Airport("Teniente Benjamín Matienzo",              "SANT", "TUC", "CBA", "Tucumán, Tucumán", true);

    static final FIR CBA = new FIR(
            "CBA",
            "Córdoba",
            new ArrayList<>(
                Arrays.asList(SANC, SACO, SACE, SASJ, SANL, SAOC, SASA, SAOS, SANR, SANE, SAST, SANT)
            )
    );

    static final private Airport SAMM = new Airport("Comodoro Salomón", "SAMM", "", "DOZ", "Malagüe, Mendoza", false);
    static final private Airport SAME = new Airport("El Plumerillo",      "SAME","DOZ", "DOZ", "Mendoza, Mendoza", true);
    static final private Airport SANU = new Airport("Domingo Faustino Sarmiento",     "SANU","JUA", "DOZ", "San Juan, San Juan", true);
    static final private Airport SAOU = new Airport("Brig. César Ojeda",     "SAOU", "UIS", "DOZ", "San Luis, San Luis", true);

    static final private Airport SAMR = new Airport("Santiago Germano",   "SAMR", "SRA", "DOZ", "San Rafael, Mendoza", true);
    static final private Airport SAOR = new Airport("Villa Reynolds",   "SAOR", "RYD", "DOZ", "Mercedes, San Luis", false);

    static final FIR DOZ = new FIR(
            "DOZ",
            "Mendoza",
            new ArrayList<>(
                Arrays.asList(SAMM, SAME, SANU, SAOU, SAMR, SAOR)
            )
    );


    static final private Airport SAVC = new Airport("General Mosconi", "SAVC", "CRV", "CRV", "Comodoro Rivadavia, Chubut", true);
    static final private Airport SAWC = new Airport("El Calafate", "SAWC", "ECA", "CRV", "El Calafate, Santa Cruz", true);
    static final private Airport SAVE = new Airport("Brigadier Antonio Parodi", "SAVE", "ESQ", "CRV", "Esquel, Chubut", true);
    static final private Airport SAWP = new Airport("Perito Moreno", "SAWP", "PTM", "CRV", "Perito Moreno, Santa Cruz", false);

    static final private Airport SAWD = new Airport("Puerto Deseado", "SAWD", "ADO", "CRV", "Puerto Deseado, Santa Cruz", false);
    static final private Airport SAVY = new Airport("El Tehuelche", "SAVY", "DRY", "CRV", "Puerto Madryn, Chubut",false);
    static final private Airport SAWG = new Airport("Piloto Civil N. Fernández", "SAWG", "GAL", "CRV", "Río Gallegos, Santa Cruz", true);
    static final private Airport SAWE = new Airport("Río Grande", "SAWE", "GRA", "CRV", "Río Grande, Tierra del Fuego", true);

    static final private Airport SAWJ = new Airport("Capitán D. Daniel Vázquez", "SAWJ", "SJU", "CRV", "San Julián, Santa Cruz", false);
    static final private Airport SAWU = new Airport("Santa Cruz", "SAWU", "SCZ", "CRV", "Santa Cruz, Santa Cruz", false);
    static final private Airport SAVT = new Airport("Almirante Zar", "SAVT", "TRE", "CRV","Trelew, Chubut", true);
    static final private Airport SAWH = new Airport("Malvinas Argentinas", "SAWH", "USU", "CRV",  "Ushuaia, Tierra del Fuego", true);
    static final private Airport SAVV = new Airport("Gobernador Castello", "SAVV", "VIE", "CRV", "Viedma, Río Negro", true);
    static final FIR CRV = new FIR(
            "CRV",
            "Comodoro Rivadavia",
            new ArrayList<>(
                Arrays.asList(SAVC, SAWC, SAVE, SAWP, SAWD, SAVY, SAWG, SAWE, SAWJ, SAWU, SAVT, SAWH, SAVV)
            )
    );

    static final private Airport SARC = new Airport("Doctor Fernando Piragine Niveryro",  "SARC", "CRR","SIS", "Corrientes, Corrientes", true);
    static final private Airport SARF = new Airport("Formosa", "SARF", "FSA", "SIS", "Formosa, Formosa", true);
    static final private Airport SARI = new Airport("Krause", "SARI", "IGU", "SIS", "Puerto Iguazú, Misiones", true);
    static final private Airport SARL = new Airport("Paso de los Libres", "SARL", "LIB", "SIS", "Paso de los Libres, Corrientes",false);

    static final private Airport SARP = new Airport("General San Martín", "SARP", "POS", "SIS", "Posadas, Misiones", true);
    static final private Airport SATR = new Airport("Reconquista", "SATR", "RTA", "SIS", "Reconquista, Santa Fe", true);
    static final private Airport SARE = new Airport("Resistencia", "SARE", "SIS", "SIS", "Resistencia, Chaco", true);

    static final FIR SIS = new FIR(
            "SIS",
            "Resistencia",
            new ArrayList<>(
                Arrays.asList(SARC, SARF, SARI, SARL, SARP, SATR, SARE)
            )
    );

    static final private Airport SAWB = new Airport("Vicecomodoro Marambio", "SAWB", "MBI","ANT", "Isla Marambio, Mar de Weddell, Antártida", true);

    static final FIR ANTARTIDA = new FIR(
            "Antártida",
            "Antártida",
            new ArrayList<Airport>(){{add(SAWB);}}
    );

    public
    static final ArrayList<Airport> allAirportsList = new ArrayList<Airport>(){{
        addAll(EZE.getAirports());
        addAll(CBA.getAirports());
        addAll(DOZ.getAirports());
        addAll(SIS.getAirports());
        addAll(CRV.getAirports());
        addAll(ANTARTIDA.getAirports());
    }};

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
        code = code.toUpperCase();
        final List<FIR> list = getFIRList();
        for(FIR fir : list ){
            final List<Airport> airports = fir.getAirports();
            for(Airport airport : airports )
                if( airport.getIcaoCode().equals(code))
                    return airport;
        }
        return null;
    }
}
