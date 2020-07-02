package com.maera.core;

/**
 * Representa una localizacion
 */
public final class Localizacion {
    private String localidad;
    private String provincia;

    Localizacion(String localidad, String provincia){
        this.localidad= localidad;
        this.provincia = provincia;
    }

    public
    String obtenerLocalidad(){
        return localidad;
    }

    public
    String obtenerProvincia(){ return provincia; }

    public
    String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(localidad);
        if( provincia == null || provincia.isEmpty() )
            return builder.toString();
        return builder.append(", ").append(provincia).toString();
    }
}
