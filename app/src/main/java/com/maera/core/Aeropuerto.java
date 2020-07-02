package com.maera.core;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Comparator;

/**
 * Representa un aeropuerto.
 * Implementa parceable para poder ser pasado de una actividad a otra.
 * Implementa comparator para poder ser comparado para ser ordenado en una lista.
 */
public final class Aeropuerto implements Parcelable, Comparator<Aeropuerto>, Comparable<Aeropuerto> {
    /**
     * El código OACI
     */
    private String  oaci;

    /**
     * El código que propone la ANAC, es decir, el código nacional
     */
    private String  codNacional;

    /**
     * Nombre del aeropuerto
     */
    private String  nombre;

    /**
     * Si el aeropuerto es internacional
     */
    private boolean internacional;

    /**
     * La localización del aeropuerto.
     */
    private Localizacion localizacion;

    /**
     * FIR a la que pertenece.
     */
    private FIR fir;

    /**
     * Si emite Terminal Aerodrom Forecast - TAF
     */
    private Boolean emiteTAF;

    /**
     * Si es un aeropuerto favorito del usuario.
     */
    private boolean favorito;

    Aeropuerto(){ }

    Aeropuerto(String nombre, String oaci, String codNacional, boolean internacional, FIR fir, String localidad, String provincia, boolean emiteTAF) {
        this.oaci = oaci;
        this.codNacional = codNacional;
        this.nombre = nombre;
        this.internacional = internacional;
        localizacion = new Localizacion(localidad,provincia);
        this.fir = fir;
        this.emiteTAF = emiteTAF;
    }

    /**
     * Obtiene el nombre del aeropuerto
     * @return nombre del aeropuerto
     */
    public
    String obtenerNombre() { return nombre; }

    /**
     * Obtiene el código OACI del aeropuerto
     * @return el código OACI
     */
    public
    String obtenerOACI() { return oaci; }

    /**
     * Obtiene el código local
     * @return el código local
     */
    public
    String obtenerCodNacional() { return codNacional; }

    /**
     * Chequea si es internacional o no
     * @return booleano
     */
    public
    boolean esInternacional(){ return internacional; }

    /**
     * Si emite o no emite TAF
     * @return booleano
     */
    public
    boolean emiteTAF() { return emiteTAF; }

    /**
     * Obtiene la localización del aeropuerto
     * @return la localización
     */
    public
    Localizacion obtenerLocalizacion(){ return localizacion; }

    /**
     * Obtiene la FIR a la que pertenece el aeropuerto
     * @return la FIR
     */
    public
    FIR obtenerFIR(){ return fir; }

    /**
     * Pregunta si es favorito o no.
     * @return booleano
     */
    public
    boolean esFavorito(){ return favorito; }

    /**
     * Setea el código OACI
     * @param oaci
     */
    void setearOACI(String oaci) { this.oaci = oaci; }

    /**
     * Setea el código nacional
     * @param codigo
     */
    void setearCodNacional(String codigo ){ codNacional = codigo; }

    /**
     * Setea la fir
     * @param fir
     */
    void setearFIR(FIR fir){ this.fir = fir; }

    /**
     * Setea el nombre
     * @param nombre
     */
    void setearNombre(String nombre){ this.nombre = nombre; }

    /**
     * Setea la localización
     * @param localidad
     * @param provincia
     */
    void setearLocalizacion( String localidad, String provincia){ localizacion = new Localizacion(localidad, provincia); }

    /**
     * Setea la emisión, o no, de TAF.
     * @param emision
     */
    void emisionTAF(boolean emision){ emiteTAF = emision; }

    /**
     * Setea el aeropuerto como favorito o no.
     * @param favorito
     */
    void setearFavorito(Boolean favorito){ this.favorito = favorito; }

    /**
     * Implementación de Parceable, necesaria para poder pasar una instancia de Aeropuerto
     * de una actividad a otra.
     */
    public
    static
    final Parcelable.Creator<Aeropuerto> CREATOR = new Parcelable.Creator<Aeropuerto>(){
        public Aeropuerto createFromParcel(Parcel in){
            return new Aeropuerto(in);
        }
        public Aeropuerto[] newArray(int size){
            return new Aeropuerto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(oaci);
        parcel.writeString(codNacional);
        parcel.writeString(nombre);
        parcel.writeInt(internacional ? 1 : 0);
        parcel.writeString(localizacion.obtenerLocalidad());
        parcel.writeString(localizacion.obtenerProvincia());
        parcel.writeString(fir.name());
        parcel.writeInt(emiteTAF ? 1 : 0);
        parcel.writeInt(favorito ? 1 : 0);
    }

    private
    Aeropuerto(Parcel in){
        oaci = in.readString();
        codNacional = in.readString();
        nombre = in.readString();
        internacional = in.readInt() == 1;
        localizacion = new Localizacion(in.readString(), in.readString());
        fir = FIR.valueOf(in.readString());
        emiteTAF = in.readInt() == 1;
        favorito = in.readInt() == 1;
    }

    public int compare(Aeropuerto a, Aeropuerto b) {
        return Boolean.compare(a.esFavorito(),b.esFavorito());
    }

    @Override
    public int compareTo(Aeropuerto aeropuerto) {
        Boolean primero = this.esFavorito();
        Boolean segundo = aeropuerto.esFavorito();
        return primero.compareTo(segundo);
    }
}
