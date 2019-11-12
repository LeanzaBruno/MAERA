package com.maera.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public final class DataBaseManager extends SQLiteOpenHelper {
    private static DataBaseManager _INSTANCE;
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "airports._dataBase";
    private static String TABLE_NAME = "Airports";

    //Data base structure
    private static String COLUMN_ICAO = "ICAO";
    private static String COLUMN_NATIONAL_CODE = "NATIONAL";
    private static String COLUMN_NAME = "NAME";
    private static String COLUMN_FIR = "FIR";
    private static String COLUMN_CITY = "CITY";
    private static String COLUMN_PROVINCE = "PROVINCE";
    private static String COLUMN_TAF_AVAILABILITY = "TAF_AVAILABILITY";
    private static String COLUMN_FAVOURITE = "FAVOURITE";

    //Data base string creator
    private static String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                                                COLUMN_ICAO + " TEXT PRIMARY KEY," +
                                                COLUMN_NATIONAL_CODE + " TEXT," +
                                                COLUMN_NAME + " TEXT," +
                                                COLUMN_FIR + " TEXT," +
                                                COLUMN_CITY + " TEXT," +
                                                COLUMN_PROVINCE + " TEXT," +
                                                COLUMN_TAF_AVAILABILITY + " INTEGER,"+
                                                COLUMN_FAVOURITE + " INTEGER)";

    private static String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static List<Airport> _airports = null;

    private DataBaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static
    public
    DataBaseManager getInstance(Context context ){
        if( _INSTANCE == null )
            _INSTANCE = new DataBaseManager(context);
        return _INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        dataBase.execSQL(SQL_CREATE_ENTRIES);
        fillTable(dataBase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        dataBase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(dataBase);
    }

    private
    void fillTable(SQLiteDatabase database){
        _airports = new ArrayList<Airport>(){{
            add(new Airport("Aeroparque Jorge Newbery", "SABE", "AEP", FIR.EZE, "Ciudad Autonónoma de Buenos Aires", null, true));
            add(new Airport("Aeropuerto Internacional Ministro Pistarini", "SAEZ", "EZE", FIR.EZE, "Ezeiza", PROVINCE.BUENOS_AIRES, true));
            add(new Airport("San Fernando", "SADF", "FDO", FIR.EZE, "San Fernando", PROVINCE.BUENOS_AIRES, true));
            add(new Airport("Aeropuerto internacional Ingeniero aeronáutico Ambrosio Taravella", "SACO", "CBA", FIR.CBA, "Córdoba", PROVINCE.CORDOBA, true));

            add(new Airport("Aeropuerto internacional Francisco Gabrielli", "SAME", "DOZ", FIR.DOZ, "Mendoza", PROVINCE.MENDOZA, true));
            add(new Airport("Aeropuerto Internacional Martín Miguel de Güemes", "SASA", "SLA", FIR.CBA, "Salta", PROVINCE.SALTA, true));
            add(new Airport("Comandante Espora", "SAZB", "", FIR.EZE, "Bahía Blanca", PROVINCE.BUENOS_AIRES, true));
            add(new Airport("Aeropuerto de Campo de Mayo", "SADO", "CPO", FIR.EZE, "San Miguel", PROVINCE.BUENOS_AIRES, false));

            add(new Airport("Aeropuerto Comodoro Juan José Pierrestegui", "SAAC", "DIA", FIR.EZE, "Concordia", PROVINCE.ENTRE_RIOS, false));
            add(new Airport("Aviador José Campos", "SAZY", "CHP", FIR.EZE, "San Martín de los Andes", PROVINCE.MENDOZA, true));
            add(new Airport("Presidente Rivadavia", "SADM", "MOR", FIR.EZE, "Morón", PROVINCE.BUENOS_AIRES, false));
            add(new Airport("General Pico", "SAZG", "GPI", FIR.EZE, "General Pico", PROVINCE.LA_PAMPA, false));

            add(new Airport("Gualeguaychú", "SAAG", "GUA", FIR.EZE, "Gualeguaychú", PROVINCE.ENTRE_RIOS,false));
            add(new Airport("Junín", "SAZY", "NIN", FIR.EZE, "Junín", PROVINCE.BUENOS_AIRES, false));
            add(new Airport("Mariano Moreno", "SADJ", "ENO", FIR.EZE, "José C. Paz", PROVINCE.BUENOS_AIRES, false));

            add(new Airport("General Urquiza", "SAAP", "PAR", FIR.EZE, "Paraná", PROVINCE.ENTRE_RIOS, true));
            add(new Airport("Santa Rosa", "SAZR", "OSA", FIR.EZE, "Santa Rosa", PROVINCE.LA_PAMPA, true));
            add(new Airport("Sauce Viejo", "SAAV", "SVO", FIR.EZE, "Sauce Viejo", PROVINCE.SANTA_FE, true));
            add(new Airport("Catamarca", "SANC", "CAT", FIR.CBA, "San Fernando del Valle de Catamarca", PROVINCE.CATAMARCA, true));

            add(new Airport("Aeropuerto Internacional Teniente Luis Candelaria", "SAZS", "BAR", FIR.EZE, "San Carlos de Bariloche", PROVINCE.RIO_NEGRO, true));
            add(new Airport("Aeropuerto Internacional de Puerto Iguazú", "SARI", "IGU", FIR.SIS, "Puerto Iguazú", PROVINCE.MISIONES, true));
            add(new Airport("Aeropuerto de El Palomar", "SADP", "PAL", FIR.EZE, "El Palomar", PROVINCE.BUENOS_AIRES, true));
            add(new Airport("Aeropuerto Internacional Presidente Perón", "SAZN", "NEU", FIR.EZE, "Neuquén", PROVINCE.NEUQUEN, true));

            add(new Airport("Aeropuerto Internacional Teniente General Benjamín Matienzo", "SANT", "TUC", FIR.CBA, "Tucumán", PROVINCE.TUCUMAN, true));
            add(new Airport("Aeropuerto Internacional Malvinas Argentinas", "SAWH", "USU", FIR.CRV, "Ushuaia", PROVINCE.TIERRA_DEL_FUEGO, true));
            add(new Airport("Aeropuerto Internacional Islas Malvinas", "SAAR", "ROS", FIR.EZE, "Rosario", PROVINCE.SANTA_FE, true));
            add(new Airport("Aeropuerto Internacional General Enrique Mosconi", "SAVC", "", FIR.CRV, "Comodoro Rivadavia", PROVINCE.CHUBUT, true));

            add(new Airport("Aeropuerto Internacional Comandante Armando Tola", "SAWC", "ECA", FIR.CRV, "El Calafate", PROVINCE.SANTA_CRUZ, true));
            add(new Airport("Aeropuerto Internacional Astor Piazzolla", "SAZM", "MDP", FIR.EZE, "Mar del Plata", PROVINCE.BUENOS_AIRES, true));
            add(new Airport("Aeropuerto de Azul", "SAZA", "ZUL", FIR.EZE, "Azul", PROVINCE.BUENOS_AIRES, false));
            add(new Airport("Escuela de Aviación", "SACE", "", FIR.CBA, "Córdoba", PROVINCE.CORDOBA, false));

            add(new Airport("Gobernador Guzmán", "SASJ", "JUJ", FIR.CBA, "Jujuy", PROVINCE.JUJUY, true));
            add(new Airport("Capitán Almonacid", "SANL", "LAR", FIR.CBA, "La Rioja", PROVINCE.LA_RIOJA, true));
            add(new Airport("Área de Material", "SAOC", "TRC", FIR.CBA, "Río Cuarto", PROVINCE.CORDOBA, true));
            add(new Airport("Santa Rosa del Conlara", "SAOS", "SRC", FIR.CBA, "Santa Rosa de Conlara", PROVINCE.SAN_LUIS, false));

            add(new Airport("Vcom. Ángel La Paz Aragonés", "SANE", "SDE", FIR.CBA, "Santiago del Estero", PROVINCE.SANTIAGO_DEL_ESTERO, true));
            add(new Airport("General Mosconi", "SAST", "TAR", FIR.CBA, "Tartagal", PROVINCE.SALTA, false));
            add(new Airport("Termas de Río Hondo", "SANR", "TRH", FIR.CBA, "Termas de Río Hondo", PROVINCE.SANTIAGO_DEL_ESTERO, true));
            add(new Airport("Aeropuerto Comodoro Ricardo Salomón", "SAMM", "LGS", FIR.DOZ, "Malargüe", PROVINCE.MENDOZA, false));

            add(new Airport("Domingo Faustino Sarmiento", "SANU", "JUA", FIR.DOZ, "San Juan", PROVINCE.SAN_JUAN, true));
            add(new Airport("Brig. César Ojeda", "SAOU", "UIS", FIR.DOZ, "San Luis", PROVINCE.SAN_LUIS, true));
            add(new Airport("Santiago Germano", "SAMR", "SRA", FIR.DOZ, "San Rafael", PROVINCE.MENDOZA, true));
            add(new Airport("Villa Reynolds", "SAOR", "RYD", FIR.DOZ, "Mercedes", PROVINCE.SAN_LUIS, false));
            add(new Airport("Brigadier Antonio Parodi", "SAVE", "ESQ", FIR.CRV, "Esquel", PROVINCE.CHUBUT, true));
            add(new Airport("Perito Moreno", "SAWP", "PTM", FIR.CRV, "Perito Moreno", PROVINCE.SANTA_CRUZ, false));
            add(new Airport("Puerto Deseado", "SAWD", "ADO", FIR.CRV, "Puerto Deseado", PROVINCE.SANTA_CRUZ, false));
            add(new Airport("El Tehuelche", "SAVY", "DRY", FIR.CRV, "Puerto Madryn", PROVINCE.CHUBUT, false));
            add(new Airport("Piloto Civil N. Fernández", "SAWG", "GAL", FIR.CRV, "Río Gallegos", PROVINCE.SANTA_CRUZ, true));
            add(new Airport("Río Grande", "SAWE", "GRA", FIR.CRV, "Río Grande", PROVINCE.TIERRA_DEL_FUEGO, true));
            add(new Airport("Capitán D. Daniel Vázquez", "SAWJ", "SJU", FIR.CRV, "San Julián", PROVINCE.SANTA_CRUZ, false));
            add(new Airport("Santa Cruz", "SAWU", "SCZ", FIR.CRV, "Santa Cruz", PROVINCE.SANTA_CRUZ, false));
            add(new Airport("Almirante Zar", "SAVT", "TRE", FIR.CRV, "Trelew", PROVINCE.CHUBUT, true));
            add(new Airport("Gobernador Castello", "SAVV", "VIE", FIR.CRV, "Viedma", PROVINCE.RIO_NEGRO, true));
            add(new Airport("Doctor Fernando Piragine Niveryro", "SARC", "CRR", FIR.SIS, "Corrientes", PROVINCE.CORRIENTES, true));
            add(new Airport("Formosa", "SARF", "FSA", FIR.SIS, "Formosa", PROVINCE.FORMOSA, true));
            add(new Airport("Paso de los Libres", "SARL", "LIB", FIR.SIS, "Paso de los Libres", PROVINCE.CORRIENTES, false));
            add(new Airport("General San Martín", "SARP", "POS", FIR.SIS, "Posadas", PROVINCE.MISIONES, true));
            add(new Airport("Reconquista", "SATR", "RTA", FIR.SIS, "Reconquista", PROVINCE.SANTA_FE, true));
            add(new Airport("Resistencia", "SARE", "SIS", FIR.SIS, "Resistencia", PROVINCE.CHACO, true));
            add(new Airport("Vicecomodoro Marambio", "SAWB", "MBI", FIR.ANTARTIDA, "Isla Marambio, Mar de Weddell", PROVINCE.TIERRA_DEL_FUEGO, true));
        }};

        for( int index = 0 ; index < _airports.size() ; ++index )
            database.insert(TABLE_NAME, null, fillRow(_airports.get(index)));
        database.close();
    }



    private
    ContentValues fillRow(Airport airport){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ICAO, airport.getIcaoCode());
        values.put(COLUMN_NATIONAL_CODE, airport.getLocalCode());
        values.put(COLUMN_NAME, airport.getName());
        values.put(COLUMN_CITY, airport.getLocation().getCity());
        values.put(COLUMN_PROVINCE, airport.getLocation().getProvince().name());
        values.put(COLUMN_FIR, airport.getFir().getCode());
        values.put(COLUMN_TAF_AVAILABILITY, airport.issuesTaf() ? 1 : 0);
        values.put(COLUMN_FAVOURITE, airport.isFavourite() ? 1 : 0);
        return values;
    }

    public
    List<Airport> getAllAirports(){
        if( _airports != null ) return _airports;
        _airports = new ArrayList<>();

        final String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){
            Airport airport = new Airport();
            airport.setIcaoCode( cursor.getString(cursor.getColumnIndex(COLUMN_ICAO)));
            airport.setNationalCode( cursor.getString(cursor.getColumnIndex(COLUMN_NATIONAL_CODE)));
            airport.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));

            final FIR fir = FIR.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_FIR)));
            airport.setFIR(fir);

            final String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            final PROVINCE province = PROVINCE.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)));
            airport.setLocation(city, province);

            airport.setTafAvailability(cursor.getInt(cursor.getColumnIndex(COLUMN_TAF_AVAILABILITY)) == 1);
            airport.setFavourite(cursor.getInt(cursor.getColumnIndex(COLUMN_FAVOURITE)) == 1);

            _airports.add(airport);
        }

        database.close();
        return _airports;
    }

    Airport getAirport(int index){
        return _airports.get(index);
    }
}
