package com.maera.core;

import androidx.annotation.NonNull;

public enum WeatherReport{
    METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
    TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
    PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar&");

    private String _url;
    WeatherReport(String url){ _url = url;}

    public String generateURL(@NonNull Aeropuerto Aeropuerto){
        if( this == METAR || this == TAF )
            return _url + Aeropuerto.obtenerOACI();
        else
            return generateURL(Aeropuerto.obtenerFIR());
    }

    public String generateURL(@NonNull FIR fir){
        return _url + fir.getCode() + "=on";
    }
}
