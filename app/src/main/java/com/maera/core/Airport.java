package com.maera.core;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Airport implements Parcelable {
    private String  mIcaoCode;
    private String  mLocalCode;
    private String  mName;
    private Location mLocation;
    private FIR mFir;
    private Boolean mHasTaf;
    private String mMetar;
    private String mtaf;
    private Boolean mFavourite = false;

    Airport(){ }

    Airport(String name, String icaoCode, String localCode, FIR fir, String city, PROVINCE province, Boolean taf) {
        mIcaoCode = icaoCode;
        mLocalCode = localCode;
        mName = name;
        mLocation = new Location(city,province);
        mFir = fir;
        mHasTaf = taf;
    }

    //Getters
    public
    String getName() { return mName; }

    public
    String getIcaoCode() { return mIcaoCode; }

    public
    String getLocalCode() { return mLocalCode; }

    public
    Boolean issuesTaf() { return mHasTaf; }

    public
    Location getLocation(){ return mLocation; }

    public
    FIR getFir(){ return mFir; }

    public
    Boolean isFavourite(){ return mFavourite; }

    public
    String getMetar(){ return mMetar; }

    public
    String getTaf(){ return mtaf; }

    //Setters
    void setIcaoCode(String icao) { mIcaoCode = icao; }

    void setNationalCode(String code ){ mLocalCode = code; }

    void setFIR(FIR fir){ mFir = fir; }

    void setName(String name){ mName = name; }

    void setLocation(@NonNull String city, @Nullable PROVINCE province){ mLocation = new Location(city, province); }

    void setTafAvailability(Boolean availability){ mHasTaf = availability; }

    public
    void setFavourite(Boolean favourite){ mFavourite = favourite; }

    public
    void setMetar(String metar) { mMetar = metar; }

    public
    void setTaf(String taf){ mtaf = taf; }

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
        parcel.writeString(mIcaoCode);
        parcel.writeString(mLocalCode);
        parcel.writeString(mName);
        parcel.writeString(mLocation.getCity());
        parcel.writeString(mLocation.getProvince().name());
        parcel.writeString(mFir.getCode());
        parcel.writeInt(mHasTaf ? 1 : 0);
        parcel.writeString(mMetar);
        parcel.writeString(mtaf);
    }

    private
    Airport(Parcel in){
        mName = in.readString();
        mIcaoCode = in.readString();
    	mLocalCode = in.readString();
    	mFir = FIR.valueOf(in.readString());
	    mLocation = new Location(in.readString(), PROVINCE.valueOf(in.readString()));
        mHasTaf = in.readInt() == 1;
        mMetar = in.readString();
        mtaf = in.readString();
    }
}