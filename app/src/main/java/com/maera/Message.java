package com.maera;

public enum Message
{
    METAR("metar&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
    TAF("taf&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO="),
    PRONAREA("pronarea&operacion=consultar&${PRONAREA}=on");

    private String _url;
    static public final String commonURL = "https://ssl.smn.gob.ar/mensajes/index.php?observacion=";

    Message(String url) { _url = url; }

    public String getUrl(){ return this._url; }
};
