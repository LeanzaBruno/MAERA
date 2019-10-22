package com.maera;

import android.os.Parcel;
import android.os.Parcelable;

final class Airport implements Parcelable {
    public static final Parcelable.Creator<Airport> CREATOR = new Parcelable.Creator<Airport>(){
        public Airport createFromParcel(Parcel in){
            return new Airport(in);
        }
        public Airport[] newArray(int size){
            return new Airport[size];
        }
    };

    private String _name;
    private String _icaoCode;
    private String _fir;
    private Boolean _hasTaf;
    private String _metar;
    private String _taf;

    Airport(String name, String icaoCode, String fir, Boolean taf) {
        _name = name;
        _icaoCode = icaoCode;
        _fir = fir;
        _hasTaf = taf;
        _metar = _taf = null;
    }

    Airport(Parcel in){
        _name = in.readString();
        _icaoCode = in.readString();
        _fir = in.readString();
        _hasTaf = in.readBoolean();
        _metar = in.readString();
        _taf = in.readString();
    }

    String getAirportName() { return _name; }

    String getIcaoCode() { return _icaoCode; }

    Boolean hasTaf() { return _hasTaf; }

    String getFir(){ return _fir; }

    void setMetar(String metar) { _metar = metar; }

    void setTaf(String taf){ _taf = taf; }

    String getMetar(){ return _metar; }

    String getTaf(){ return _taf; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_name);
        parcel.writeString(_icaoCode);
        parcel.writeString(_fir);
        parcel.writeBoolean(_hasTaf);
        parcel.writeString(_metar);
        parcel.writeString(_taf);
    }
}
