package com.maera.core;

import androidx.annotation.NonNull;

public enum FIR{
    EZE("EZE", "Ezeiza"),
    CBA("CBA", "Córdoba"),
    SIS("SIS", "Resistencia"),
    DOZ("DOZ", "Mendoza"),
    CRV("CRV", "Comodoro Rivadavia"),
    ANTARTIDA("ANT", "Antártida");
    private String _code;
    private String _name;
    FIR(String code, String name) {
        _code = code;
        _name = name;
    }

    @NonNull
    public
    String getCode(){ return _code; }

    @Override
    @NonNull
    public
    String toString(){ return _name; }
}
