package com.maera.core;

import androidx.annotation.NonNull;

public enum PROVINCE {
    BUENOS_AIRES("Buenos Aires"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Córdoba"),
    CORRIENTES("Corrientes"),
    ENTRE_RIOS("Entre Ríos"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LA_PAMPA("La Pampa"),
    LA_RIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquén"),
    RIO_NEGRO("Río Negro"),
    SALTA("Salta"),
    SAN_JUAN("San Juan"),
    SAN_LUIS("San Luis"),
    SANTA_CRUZ("Santa Cruz"),
    SANTA_FE("Santa Fe"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    TIERRA_DEL_FUEGO("Tierra del Fuego, islas del Atlántico Sur y Antártida"),
    TUCUMAN("Tucumán"),
    NON_PROVINCE("");

    private String _name;
    PROVINCE(String name){_name = name;}

    @Override
    @NonNull
    public
    String toString(){ return _name; }

    public
    PROVINCE getProvince(String province) {
        switch (province) {
            case "BUENOS_AIRES":
                return BUENOS_AIRES;
            case "CATAMARCA":
                return CATAMARCA;
            case "CHACO":
                return CHACO;
            case "CHUBUT":
                return CHUBUT;
            case "CORDOBA":
                return CORDOBA;
            case "CORRIENTES":
                return CORRIENTES;
            case "ENTRE_RIOS":
                return ENTRE_RIOS;
            case "FORMOSA":
                return FORMOSA;
            case "JUJUY":
                return JUJUY;
            case "LA_PAMPA":
                return LA_PAMPA;
            case "LA_RIOJA":
                return LA_RIOJA;
            case "MENDOZA":
                return MENDOZA;
            case "MISIONES":
                return MISIONES;
            case "NEUQUEN":
                return NEUQUEN;
            case "RIO_NEGRO":
                return RIO_NEGRO;
            case "SALTA":
                return SALTA;
            case "SAN_JUAN":
                return SAN_JUAN;
            case "SAN_LUIS":
                return SAN_LUIS;
            case "SANTA_CRUZ":
                return SANTA_CRUZ;
            case "SANTA_FE":
                return SANTA_FE;
            case "SANTIAGO_DEL_ESTERO":
                return SANTIAGO_DEL_ESTERO;
            case "TIERRA_DEL_FUEGO":
                return TIERRA_DEL_FUEGO;
            case "TUCUMAN":
                return TUCUMAN;
            default:
                return NON_PROVINCE;
        }
    }
}
