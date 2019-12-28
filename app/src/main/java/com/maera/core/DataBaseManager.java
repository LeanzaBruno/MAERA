package com.maera.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public final class DataBaseManager extends SQLiteOpenHelper {
    private static DataBaseManager _INSTANCE;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "airports._dataBase";
    private static final String TABLE_NAME = "Airports";

    //Data base structure
    private static final String COL_ICAO = "ICAO";
    private static final String COL_NATIONAL_CODE = "NATIONAL";
    private static final String COL_NAME = "NAME";
    private static final String COL_FIR = "FIR";
    private static final String COL_CITY = "CITY";
    private static final String COL_PROVINCE = "PROVINCE";
    private static final String COL_TAF_AVAILABILITY = "TAF_AVAILABILITY";
    private static final String COL_FAVOURITE = "FAVOURITE";

    //Data base string creator
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                                                COL_ICAO + " TEXT PRIMARY KEY," +
                                                COL_NATIONAL_CODE + " TEXT," +
                                                COL_NAME + " TEXT," +
                                                COL_FIR + " TEXT," +
                                                COL_CITY + " TEXT," +
                                                COL_PROVINCE + " TEXT," +
                                                COL_TAF_AVAILABILITY + " INTEGER,"+
                                                COL_FAVOURITE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private boolean _dataUpdated;

    static public DataBaseManager getInstance(Context context){
        if( _INSTANCE == null )
            _INSTANCE = new DataBaseManager(context);
        return _INSTANCE;
    }

    private DataBaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        dataBase.execSQL(SQL_CREATE_ENTRIES);
        loadData(dataBase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        dataBase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(dataBase);
    }


    private void loadData(SQLiteDatabase database){
        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport("Aeropuerto internacional Jorge Newbery", "SABE", "AEP", FIR.EZE, "Ciudad Autonónoma de Buenos Aires", "", true));
        airports.add(new Airport("Aeropuerto internacional Ministro Pistarini", "SAEZ", "EZE", FIR.EZE, "Ezeiza", "Buenos Aires", true));
        airports.add(new Airport("Aeropuerto internacional de San Fernando", "SADF", "FDO", FIR.EZE, "San Fernando", "Buenos Aires", true));
        airports.add(new Airport("Aeropuerto internacional Ing. Ambrosio Taravella", "SACO", "CBA", FIR.CBA, "Córdoba", "Córdoba", true));

        airports.add(new Airport("Aeropuerto internacional Gobernador Gabrielli", "SAME", "DOZ", FIR.DOZ, "Mendoza", "Mendoza", true));
        airports.add(new Airport("Aeropuerto internacional Miguel de Güemes", "SASA", "SLA", FIR.CBA, "Salta", "Salta", true));
        airports.add(new Airport("Aeropuerto Comandante Espora", "SAZB", "", FIR.EZE, "Bahía Blanca", "Buenos Aires", true));
        airports.add(new Airport("Aeropuerto militar Campo de Mayo", "SADO", "CPO", FIR.EZE, "San Miguel", "Buenos Aires", false));

        airports.add(new Airport("Aeropuerto Comodoro Pierrestegui", "SAAC", "DIA", FIR.EZE, "Concordia", "Entre Ríos", false));
        airports.add(new Airport("Aeropuerto Aviador Carlos Campos", "SAZY", "CHP", FIR.EZE, "San Martín de los Andes", "Neuquén", true));
        airports.add(new Airport("Aeropuerto de Morón", "SADM", "MOR", FIR.EZE, "Morón", "Buenos Aires", false));
        airports.add(new Airport("Aeropuerto de General Pico", "SAZG", "GPI", FIR.EZE, "General Pico", "La Pampa", false));

        airports.add(new Airport("Aeropuerto de Gualeguaychú", "SAAG", "GUA", FIR.EZE, "Gualeguaychú", "Entre Ríos",false));
        airports.add(new Airport("Aeropuerto de Junín", "SAAJ", "NIN", FIR.EZE, "Junín", "Buenos Aires", false));
        airports.add(new Airport("Aeropuerto Mariano Moreno", "SADJ", "ENO", FIR.EZE, "José C. Paz", "Buenos Aires", false));

        airports.add(new Airport("Aeropuerto General Urquiza", "SAAP", "PAR", FIR.EZE, "Paraná", "Entre Ríos", true));
        airports.add(new Airport("Aeropuerto de Santa Rosa", "SAZR", "OSA", FIR.EZE, "Santa Rosa", "La Pampa", true));
        airports.add(new Airport("Aeropuerto de Sauce Viejo", "SAAV", "SVO", FIR.EZE, "Sauce Viejo", "Santa Fe", true));
        airports.add(new Airport("Aeropuerto Coronel Felipe Varela", "SANC", "CAT", FIR.CBA, "San Fernando del Valle de Catamarca", "Catamarca", true));

        airports.add(new Airport("Aeropuerto internacional Teniente Luis Candelaria", "SAZS", "BAR", FIR.EZE, "San Carlos de Bariloche", "Río Negro", true));
        airports.add(new Airport("Aeropuerto internacional de Puerto Iguazú", "SARI", "IGU", FIR.SIS, "Puerto Iguazú", "Misiones", true));
        airports.add(new Airport("Aeropuerto internacional de El Palomar", "SADP", "PAL", FIR.EZE, "El Palomar", "Buenos Aires", true));
        airports.add(new Airport("Aeropuerto internacional Presidente Perón", "SAZN", "NEU", FIR.EZE, "Neuquén", "Neuquén", true));

        airports.add(new Airport("Aeropuerto internacional Teniente General Matienzo", "SANT", "TUC", FIR.CBA, "Tucumán", "Tucumán", true));
        airports.add(new Airport("Aeropuerto internacional Malvinas Argentinas", "SAWH", "USU", FIR.CRV, "Ushuaia", "Tierra del Fuego, Islas del Atlántico Sur y Antártida", true));
        airports.add(new Airport("Aeropuerto internacional Islas Malvinas", "SAAR", "ROS", FIR.EZE, "Rosario", "Santa Fe", true));
        airports.add(new Airport("Aeropuerto internacional General Mosconi", "SAVC", "", FIR.CRV, "Comodoro Rivadavia", "Chubut", true));

        airports.add(new Airport("Aeropuerto internacional Comandante Tola", "SAWC", "ECA", FIR.CRV, "El Calafate", "Santa Cruz", true));
        airports.add(new Airport("Aeropuerto internacional Astor Piazzolla", "SAZM", "MDP", FIR.EZE, "Mar del Plata", "Buenos Aires", true));
        airports.add(new Airport("Aeropuerto de Azul", "SAZA", "ZUL", FIR.EZE, "Azul", "Buenos Aires", false));
        airports.add(new Airport("Escuela Militar de Aviación", "SACE", "FMA", FIR.CBA, "Córdoba", "Córdoba", false));

        airports.add(new Airport("Aeropuerto internacional Gobernador Guzmán", "SASJ", "JUJ", FIR.CBA, "San Salvador de Jujuy", "Jujuy", true));
        airports.add(new Airport("Aeropuerto Capitán Almonacid", "SANL", "LAR", FIR.CBA, "La Rioja","La Rioja", true));
        airports.add(new Airport("Aeropuerto de Río Cuarto", "SAOC", "TRC", FIR.CBA, "Río Cuarto", "Córdoba", true));
        airports.add(new Airport("Aeropuerto internacional Valle del Conlara", "SAOS", "SRC", FIR.CBA, "Santa Rosa de Conlara", "San Luis", false));

        airports.add(new Airport("Aeropuerto de Santiago del Estero", "SANE", "SDE", FIR.CBA, "Santiago del Estero", "Santiago del Estero", true));
        airports.add(new Airport("Aeropuerto General Mosconi", "SAST", "TAR", FIR.CBA, "Tartagal", "Salta", false));
        airports.add(new Airport("Aeropuerto internacional de Termas de Río Hondo", "SANR", "TRH", FIR.CBA, "Termas de Río Hondo", "Santiago del Estero", true));
        airports.add(new Airport("Aeropuerto Comodoro Salomón", "SAMM", "LGS", FIR.DOZ, "Malargüe", "Mendoza", false));

        airports.add(new Airport("Aeropuerto internacional Sarmiento", "SANU", "JUA", FIR.DOZ, "San Juan", "San Juan", true));
        airports.add(new Airport("Aeropuerto Brigadier Mayor Ojeda", "SAOU", "UIS", FIR.DOZ, "San Luis", "San Luis", true));
        airports.add(new Airport("Aeropuerto Santiago Germano", "SAMR", "SRA", FIR.DOZ, "San Rafael", "Mendoza", true));
        airports.add(new Airport("Aeropuerto de Villa Reynolds", "SAOR", "RYD", FIR.DOZ, "Mercedes", "San Luis", false));

        airports.add(new Airport("Aeropuerto internacional Brigadier General Parodi", "SAVE", "ESQ", FIR.CRV, "Esquel", "Chubut", true));
        airports.add(new Airport("Aeropuerto de Perito Moreno", "SAWP", "PTM", FIR.CRV, "Perito Moreno", "Santa Cruz", false));
        airports.add(new Airport("Aeropuerto de Puerto Deseado", "SAWD", "ADO", FIR.CRV, "Puerto Deseado", "Santa Cruz", false));
        airports.add(new Airport("Aeropuerto El Tehuelche", "SAVY", "DRY", FIR.CRV, "Puerto Madryn", "Chubut", false));

        airports.add(new Airport("Aeropuerto internacional Norberto Fernández", "SAWG", "GAL", FIR.CRV, "Río Gallegos", "Santa Cruz", true));
        airports.add(new Airport("Aeropuerto internacional Gobernador Noel", "SAWE", "GRA", FIR.CRV, "Río Grande", "Tierra del Fuego", true));
        airports.add(new Airport("Aeropuerto Capitán D. Daniel Vázquez", "SAWJ", "SJU", FIR.CRV, "San Julián", "Santa Cruz", false));
        airports.add(new Airport("Aeropuerto de Puerto Santa Cruz", "SAWU", "SCZ", FIR.CRV, "Puerto Santa Cruz", "Santa Cruz", false));

        airports.add(new Airport("Aeropuerto internacional Almirante Zar", "SAVT", "TRE", FIR.CRV, "Trelew", "Chubut", true));
        airports.add(new Airport("Aeropuerto Gobernador Castello", "SAVV", "VIE", FIR.CRV, "Viedma", "Río Negro", true));
        airports.add(new Airport("Aeropuerto internacional Dr. Piragine Niveyro", "SARC", "CRR", FIR.SIS, "Corrientes", "Corrientes", true));
        airports.add(new Airport("Aeropuerto internacional de Formosa", "SARF", "FSA", FIR.SIS, "Formosa", "Formosa", true));

        airports.add(new Airport("Aeropuerto internacional de Paso de los Libres", "SARL", "LIB", FIR.SIS, "Paso de los Libres", "Corrientes", false));
        airports.add(new Airport("Aeropuerto internacional General San Martín", "SARP", "POS", FIR.SIS, "Posadas", "Misiones", true));
        airports.add(new Airport("Aeropuerto Daniel Jukic", "SATR", "RTA", FIR.SIS, "Reconquista", "Santa Fe", true));
        airports.add(new Airport("Aeropuerto internacional de Resistencia", "SARE", "SIS", FIR.SIS, "Resistencia", "Chaco", true));

        airports.add(new Airport("Aeródromo Vicecomodoro Marambio", "SAWB", "MBI", FIR.CRV, "Isla Marambio, Mar de Weddell", "Tierra del Fuego", true));

        for( int index = 0 ; index < airports.size() ; ++index )
            database.insert(TABLE_NAME, null, fillRow(airports.get(index)));
    }

    private ContentValues fillRow(Airport airport){
        ContentValues values = new ContentValues();
        values.put(COL_ICAO, airport.getIcaoCode());
        values.put(COL_NATIONAL_CODE, airport.getLocalCode());
        values.put(COL_NAME, airport.getName());
        values.put(COL_CITY, airport.getLocation().getLocality());
        values.put(COL_PROVINCE, airport.getLocation().getProvince());
        values.put(COL_FIR, airport.getFir().name());
        values.put(COL_TAF_AVAILABILITY, airport.issuesTaf());
        values.put(COL_FAVOURITE, airport.isFavourite());
        return values;
    }

    public @NonNull List<Airport> getAllAirports(){
        List<Airport> airports = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, COL_FAVOURITE +" DESC," + COL_ICAO);
        while(cursor.moveToNext())
            airports.add(generateAirport(cursor));
        cursor.close();
        return airports;
    }

    private Airport generateAirport(Cursor cursor){
        Airport airport = new Airport();
        airport.setIcaoCode( cursor.getString(cursor.getColumnIndex(COL_ICAO)));
        airport.setNationalCode( cursor.getString(cursor.getColumnIndex(COL_NATIONAL_CODE)));
        airport.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));

        final FIR fir = FIR.valueOf(cursor.getString(cursor.getColumnIndex(COL_FIR)));
        airport.setFIR(fir);

        final String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
        final String province = cursor.getString(cursor.getColumnIndex(COL_PROVINCE));
        airport.setLocation(city, province);

        airport.setTafAvailability(cursor.getInt(cursor.getColumnIndex(COL_TAF_AVAILABILITY)) == 1);
        airport.setFavourite(cursor.getInt(cursor.getColumnIndex(COL_FAVOURITE)) == 1);

        return airport;
    }

    public
    void setFavourite(Airport airport , Boolean favourite){
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_FAVOURITE, favourite ? 1 : 0);
            final String whereClause = COL_ICAO + " = ?";
            final String[] args = new String[]{airport.getIcaoCode()};
            database.update(TABLE_NAME, values, whereClause, args);
            database.setTransactionSuccessful();
        }
        catch( Exception e) { e.printStackTrace(); }
        finally{
            _dataUpdated = false;
            database.endTransaction();
        }
    }
}
