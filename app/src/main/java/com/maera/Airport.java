package com.maera;

import android.os.Parcel;
import android.os.Parcelable;

final class Airport implements Parcelable {
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
        mMetar = mtaf = null;
    }

    //Getters
    String getAirportName() { return mName; }

    String getIcaoCode() { return mIcaoCode; }
    
    String getLocalCode() { return mLocalCode; }

    Boolean hasTaf() { return mHasTaf; }
    
    String getLocation(){ return mLocation; }

    String getFir(){ return mFir; }

    Boolean isFavourite(){ return mFavourite; }

    //Setters

    void setMetar(String metar) { mMetar = metar; }

    void setTaf(String taf){ mtaf = taf; }

    String getMetar(){ return mMetar; }

    String getTaf(){ return mtaf; }

    void setFavourite(Boolean favourite){ mFavourite = favourite; }

    //Parcel implementation
    public static final Parcelable.Creator<Airport> CREATOR = new Parcelable.Creator<Airport>(){
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
        parcel.writeBoolean(mHasTaf);
        parcel.writeString(mMetar);
        parcel.writeString(mtaf);
    }

    Airport(Parcel in){
        mName = in.readString();
        mIcaoCode = in.readString();
    	mLocalCode = in.readString();
        mFir = in.readString();
	    mLocation = in.readString();
        mHasTaf = in.readBoolean();
        mMetar = in.readString();
        mtaf = in.readString();
    }
}
