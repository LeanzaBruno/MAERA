package com.maera;

import java.util.ArrayList;

public enum FIR {
    EZE("FIR EZE"),
    CBA("FIR CBA"),
    DOZ("FIR DOZ"),
    CRV("FIR CRV"),
    SIS("FIR SIS");

    private String _name;

    FIR(String name){ _name = name; }

    public String getFirName(){ return _name; }

    static public ArrayList<FIR> getFirList(){
        ArrayList<FIR> list = new ArrayList<>();
        list.add(FIR.EZE);
        list.add(FIR.CBA);
        list.add(FIR.CRV);
        list.add(FIR.DOZ);
        list.add(FIR.SIS);
        return list;
    }
}
