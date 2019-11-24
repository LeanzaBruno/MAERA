package com.maera.core;

import androidx.annotation.NonNull;

public enum FIR{
    EZE("Ezeiza"),
    CBA("Córdoba"),
    SIS("Resistencia"),
    DOZ("Mendoza"),
    CRV("Comodoro Rivadavia"),
    ANT("Antártida");
    private String _name;
    FIR(String name) { _name = name; }

    @Override
    @NonNull
    public
    String toString(){ return _name; }
}
