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
    private static final String DATABASE_NAME = "Aeropuertos._dataBase";
    private static final String TABLE_NAME = "Aeropuertos";

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
        List<Aeropuerto> Aeropuertos = new ArrayList<>();
        Aeropuertos.add(new Aeropuerto("Aeroparque Jorge Newbery",
                "SABE", "AEP", false, FIR.EZE,
                "", "Ciudad Autonónoma de Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("Ministro Pistarini",
                "SAEZ", "EZE", true, FIR.EZE,
                "Ezeiza", "Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("",
                "SADF", "FDO", true, FIR.EZE,
                "San Fernando", "Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("Ing. Ambrosio Taravella",
                "SACO", "CBA", true, FIR.CBA,
                "Córdoba", "Córdoba", true));

        Aeropuertos.add(new Aeropuerto("Gobernador Gabrielli",
                "SAME", "DOZ", true, FIR.DOZ,
                "Mendoza", "Mendoza", true));

        Aeropuertos.add(new Aeropuerto("Miguel de Güemes",
                "SASA", "SLA", true, FIR.CBA,
                "Salta", "Salta", true));

        Aeropuertos.add(new Aeropuerto("Comandante Espora",
                "SAZB", "BCA", false, FIR.EZE,
                "Bahía Blanca", "Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("Campo de Mayo",
                "SADO", "CPO", false, FIR.EZE,
                "San Miguel", "Buenos Aires", false));

        Aeropuertos.add(new Aeropuerto("Comodoro Pierrestegui",
                "SAAC", "DIA", false, FIR.EZE,
                "Concordia", "Entre Ríos", false));

        Aeropuertos.add(new Aeropuerto("Aviador Carlos Campos",
                "SAZY", "CHP", false, FIR.EZE,
                "San Martín de los Andes", "Neuquén", true));

        Aeropuertos.add(new Aeropuerto("",
                "SADM", "MOR", false,  FIR.EZE,
                "Morón", "Buenos Aires", false));

        Aeropuertos.add(new Aeropuerto("",
                "SAZG", "GPI", false, FIR.EZE,
                "General Pico", "La Pampa", false));

        Aeropuertos.add(new Aeropuerto("",
                "SAAG", "GUA", false,  FIR.EZE,
                "Gualeguaychú", "Entre Ríos",false));

        Aeropuertos.add(new Aeropuerto( "",
                "SAAJ", "NIN", false, FIR.EZE,
                "Junín", "Buenos Aires", false));

        Aeropuertos.add(new Aeropuerto("Mariano Moreno",
                "SADJ", "ENO", false, FIR.EZE,
                "José C. Paz", "Buenos Aires", false));

        Aeropuertos.add(new Aeropuerto("General Urquiza",
                "SAAP", "PAR", false, FIR.EZE,
                "Paraná", "Entre Ríos", true));

        Aeropuertos.add(new Aeropuerto("",
                "SAZR", "OSA", false, FIR.EZE,
                "Santa Rosa", "La Pampa", true));

        Aeropuertos.add(new Aeropuerto("",
                "SAAV", "SVO", false, FIR.EZE,
                "Sauce Viejo", "Santa Fe", true));

        Aeropuertos.add(new Aeropuerto("Coronel Felipe Varela",
                "SANC", "CAT", false, FIR.CBA,
                "San Fernando del Valle de Catamarca", "Catamarca", true));

        Aeropuertos.add(new Aeropuerto("Teniente Luis Candelaria",
                "SAZS", "BAR", true, FIR.EZE,
                "San Carlos de Bariloche", "Río Negro", true));

        Aeropuertos.add(new Aeropuerto("",
                "SARI", "IGU", true, FIR.SIS,
                "Puerto Iguazú", "Misiones", true));

        Aeropuertos.add(new Aeropuerto("",
                "SADP", "PAL", true, FIR.EZE,
                "El Palomar", "Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("Presidente Perón",
                "SAZN", "NEU", true, FIR.EZE,
                "Neuquén", "Neuquén", true));

        Aeropuertos.add(new Aeropuerto("Teniente General Matienzo",
                "SANT", "TUC", true, FIR.CBA,
                "Tucumán", "Tucumán", true));

        Aeropuertos.add(new Aeropuerto("Malvinas Argentinas",
                "SAWH", "USU", true, FIR.CRV,
                "Ushuaia", "Tierra del Fuego, Islas del Atlántico Sur y Antártida", true))$;

        Aeropuertos.add(new Aeropuerto("Islas Malvinas",
                "SAAR", "ROS", true, FIR.EZE,
                "Rosario", "Santa Fe", true));

        Aeropuertos.add(new Aeropuerto("General Mosconi",
                "SAVC", "CRV", true, FIR.CRV,
                "Comodoro Rivadavia", "Chubut", true));

        Aeropuertos.add(new Aeropuerto("Comandante Tola",
                "SAWC", "ECA", true, FIR.CRV,
                "El Calafate", "Santa Cruz", true));

        Aeropuertos.add(new Aeropuerto("Astor Piazzolla",
                "SAZM", "MDP", true, FIR.EZE,
                "Mar del Plata", "Buenos Aires", true));

        Aeropuertos.add(new Aeropuerto("",
                "SAZA", "ZUL", false, FIR.EZE,
                "Azul", "Buenos Aires", false));

        Aeropuertos.add(new Aeropuerto("Escuela Militar de Aviación",
                "SACE", "FMA", FIR.CBA,
                "Córdoba", "Córdoba", false));

        Aeropuertos.add(new Aeropuerto("Gobernador Guzmán",
                "SASJ", "JUJ", true, FIR.CBA,
                "San Salvador de Jujuy", "Jujuy", true));

        Aeropuertos.add(new Aeropuerto("Capitán Almonacid",
                "SANL", "LAR", false, FIR.CBA,
                "La Rioja","La Rioja", true));

        Aeropuertos.add(new Aeropuerto("",
                "SAOC", "TRC", true, FIR.CBA,
                "Río Cuarto", "Córdoba", true));

        Aeropuertos.add(new Aeropuerto("Valle del Conlara",
                "SAOS", "SRC", true, FIR.CBA,
                "Santa Rosa de Conlara", "San Luis", false));

        Aeropuertos.add(new Aeropuerto("",
                "SANE", "SDE", false, FIR.CBA,
                "Santiago del Estero", "Santiago del Estero", true));

        Aeropuertos.add(new Aeropuerto("General Mosconi",
                "SAST", "TAR", true, FIR.CBA,
                "Tartagal", "Salta", false));

        Aeropuertos.add(new Aeropuerto("",
                "SANR", "TRH", true, FIR.CBA,
                "Termas de Río Hondo", "Santiago del Estero", true));

        Aeropuertos.add(new Aeropuerto("Comodoro Salomón",
                "SAMM", "LGS", true, FIR.DOZ,
                "Malargüe", "Mendoza", false));

        Aeropuertos.add(new Aeropuerto("Domingo Sarmiento",
                "SANU", "JUA", true, FIR.DOZ,
                "San Juan", "San Juan", true));

        Aeropuertos.add(new Aeropuerto("Brigadier Mayor Ojeda",
                "SAOU", "UIS", false, FIR.DOZ,
                "San Luis", "San Luis", true));

        Aeropuertos.add(new Aeropuerto("Santiago Germano",
                "SAMR", "SRA", false, FIR.DOZ,
                "San Rafael", "Mendoza", true));

        Aeropuertos.add(new Aeropuerto("Villa Reynolds",
                "SAOR", "RYD", false, FIR.DOZ,
                "Mercedes", "San Luis", false));

        Aeropuertos.add(new Aeropuerto("Brigadier General Parodi",
                "SAVE", "ESQ", true, FIR.CRV,
                "Esquel", "Chubut", true));

        Aeropuertos.add(new Aeropuerto("",
                "SAWP", "PTM", false, FIR.CRV,
                "Perito Moreno", "Santa Cruz", false));

        Aeropuertos.add(new Aeropuerto("", "SAWD", "ADO", false, FIR.CRV, "Puerto Deseado", "Santa Cruz", false));

        Aeropuertos.add(new Aeropuerto("El Tehuelche", "SAVY", "DRY", false, FIR.CRV, "Puerto Madryn", "Chubut", false));

        Aeropuertos.add(new Aeropuerto("Norberto Fernández", "SAWG", "GAL", true, FIR.CRV, "Río Gallegos", "Santa Cruz", true));

        Aeropuertos.add(new Aeropuerto("Gobernador Noel", "SAWE", "GRA", true, FIR.CRV, "Río Grande", "Tierra del Fuego", true));

        Aeropuertos.add(new Aeropuerto("Capitán D. Daniel Vázquez", "SAWJ", "SJU", false, FIR.CRV, "San Julián", "Santa Cruz", false));

        Aeropuertos.add(new Aeropuerto("", "SAWU", "SCZ", false, FIR.CRV, "Puerto Santa Cruz", "Santa Cruz", false));

        Aeropuertos.add(new Aeropuerto("Almirante Zar", "SAVT", "TRE", true, FIR.CRV, "Trelew", "Chubut", true));

        Aeropuertos.add(new Aeropuerto("Gobernador Castello", "SAVV", "VIE", false, FIR.CRV, "Viedma", "Río Negro", true));

        Aeropuertos.add(new Aeropuerto("Dr. Piragine Niveyro", "SARC", "CRR", true, FIR.SIS, "Corrientes", "Corrientes", true));

        Aeropuertos.add(new Aeropuerto("", "SARF", "FSA", true, FIR.SIS, "Formosa", "Formosa", true));

        Aeropuertos.add(new Aeropuerto("", "SARL", "LIB", true, FIR.SIS, "Paso de los Libres", "Corrientes", false));

        Aeropuertos.add(new Aeropuerto("General San Martín", "SARP", "POS", true, FIR.SIS, "Posadas", "Misiones", true));

        Aeropuertos.add(new Aeropuerto("Daniel Jukic", "SATR", "RTA", false, FIR.SIS, "Reconquista", "Santa Fe", true));

        Aeropuertos.add(new Aeropuerto("", "SARE", "SIS", true, FIR.SIS, "Resistencia", "Chaco", true));

        Aeropuertos.add(new Aeropuerto("Aeródromo Vicecomodoro Marambio", "SAWB", "MBI", false, FIR.CRV, "Isla Marambio, Mar de Weddell", "Tierra del Fuego", true));

        for( int index = 0 ; index < Aeropuertos.size() ; ++index )
            database.insert(TABLE_NAME, null, fillRow(Aeropuertos.get(index)));
    }

    private ContentValues fillRow(Aeropuerto Aeropuerto){
        ContentValues values = new ContentValues();
        values.put(COL_ICAO, Aeropuerto.obtenerOACI());
        values.put(COL_NATIONAL_CODE, Aeropuerto.obtenerCodNacional());
        values.put(COL_NAME, Aeropuerto.obtenerNombre());
        values.put(COL_CITY, Aeropuerto.obtenerLocalizacion().obtenerLocalidad());
        values.put(COL_PROVINCE, Aeropuerto.obtenerLocalizacion().obtenerProvincia());
        values.put(COL_FIR, Aeropuerto.obtenerFIR().name());
        values.put(COL_TAF_AVAILABILITY, Aeropuerto.emiteTAF());
        values.put(COL_FAVOURITE, Aeropuerto.esFavorito());
        return values;
    }

    public @NonNull List<Aeropuerto> getAllAeropuertos(){
        List<Aeropuerto> Aeropuertos = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, COL_FAVOURITE +" DESC," + COL_ICAO);
        while(cursor.moveToNext())
            Aeropuertos.add(generateAeropuerto(cursor));
        cursor.close();
        return Aeropuertos;
    }

    private Aeropuerto generateAeropuerto(Cursor cursor){
        Aeropuerto Aeropuerto = new Aeropuerto();
        Aeropuerto.setOACI( cursor.getString(cursor.getColumnIndex(COL_ICAO)));
        Aeropuerto.setNationalCode( cursor.getString(cursor.getColumnIndex(COL_NATIONAL_CODE)));
        Aeropuerto.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));

        final FIR fir = FIR.valueOf(cursor.getString(cursor.getColumnIndex(COL_FIR)));
        Aeropuerto.setFIR(fir);

        final String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
        final String provincia = cursor.getString(cursor.getColumnIndex(COL_PROVINCE));
        Aeropuerto.setLocalizacion(city, provincia);

        Aeropuerto.setTafAvailability(cursor.getInt(cursor.getColumnIndex(COL_TAF_AVAILABILITY)) == 1);
        Aeropuerto.setFavourite(cursor.getInt(cursor.getColumnIndex(COL_FAVOURITE)) == 1);

        return Aeropuerto;
    }

    public
    void setFavourite(Aeropuerto Aeropuerto , Boolean favourite){
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_FAVOURITE, favourite ? 1 : 0);
            final String whereClause = COL_ICAO + " = ?";
            final String[] args = new String[]{Aeropuerto.obtenerOACI()};
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
