package com.maera.core;

import androidx.annotation.NonNull;

public enum FIR{
    EZE("Ezeiza", 87582),
    CBA("Córdoba", 87344),
    SIS("Resistencia", 87155),
    DOZ("Mendoza", 87418),
    CRV("Comodoro Rivadavia", 87860),
    ANT("Antártida", 89055);
    private String _name;
    private int _code;
    FIR(String name, int code) { _name = name; _code = code; }

    @Override
    @NonNull
    public
    String toString(){ return _name; }

    public int getCode(){
        return _code;
    }
}
