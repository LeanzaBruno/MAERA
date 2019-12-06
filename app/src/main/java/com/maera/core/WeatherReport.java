package com.maera.core;

import android.os.Parcel;
import android.os.Parcelable;

public
class WeatherReport implements Parcelable {
    public enum TYPE{
        METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
        TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
        PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar&");

        private String _url;
        TYPE(String url){ _url = url;}
        public String getURL(){ return _url; }
    }

    private String _report;

    WeatherReport(String report) { _report = report; }

    private WeatherReport(Parcel input){
        _report = input.readString();
    }

    public static final Parcelable.Creator<WeatherReport> CREATOR = new Parcelable.Creator<WeatherReport>(){
        public WeatherReport createFromParcel(Parcel input){
            return new WeatherReport(input);
        }

        public WeatherReport[] newArray(int size){
            return new WeatherReport[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_report);
    }

    String getReport(){ return _report; }

}
