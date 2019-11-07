package com.maera.core;

import android.os.Parcel;
import android.os.Parcelable;

public final class Airport implements Parcelable {
    private String mName;
    private String mIcaoCode;
    private String mLocalCode;
    private String mFir;
    private String mLocation;
    private Boolean mHasTaf;
    private String mMetar;
    private String mtaf;
    private Boolean mFavourite = false;

    Airport(String name, String icaoCode, String localCode, String fir, String location, Boolean taf) { 
    	mLocation = location;
        mName = name;
        mIcaoCode = icaoCode;
	    mLocalCode = localCode;
        mFir = fir;
        mHasTaf = taf;
    }

    //Getters
    public
    String getAirportName() { return mName; }

    public
    String getIcaoCode() { return mIcaoCode; }

    public
    String getLocalCode() { return mLocalCode; }

    public
    Boolean issuesTaf() { return mHasTaf; }

    public
    String getLocation(){ return mLocation; }

    public
    String getFir(){ return mFir; }

    public
    Boolean isFavourite(){ return mFavourite; }

    public
    String getMetar(){ return mMetar; }

    public
    String getTaf(){ return mtaf; }

    //Setters
    public
    void setMetar(String metar) { mMetar = metar; }

    public
    void setTaf(String taf){ mtaf = taf; }

    public
    void setFavourite(Boolean favourite){ mFavourite = favourite; }

    //Parcel implementation
    public
    static
    final Parcelable.Creator<Airport> CREATOR = new Parcelable.Creator<Airport>(){
        public Airport createFromParcel(Parcel in){
            return new Airport(in);
        }
        public Airport[] newArray(int size){
            return new Airport[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mIcaoCode);
    	parcel.writeString(mLocalCode);
        parcel.writeString(mFir);
	    parcel.writeString(mLocation);
        parcel.writeInt(mHasTaf ? 1 : 0);
        parcel.writeString(mMetar);
        parcel.writeString(mtaf);
    }

    private
    Airport(Parcel in){
        mName = in.readString();
        mIcaoCode = in.readString();
    	mLocalCode = in.readString();
        mFir = in.readString();
	    mLocation = in.readString();
        mHasTaf = in.readInt() == 1;
        mMetar = in.readString();
        mtaf = in.readString();
    }
}
