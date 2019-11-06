package com.maera.core;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    private
    void
    downloadWeatherMessage(WeatherReport.TYPE type){
        if( type == WeatherReport.TYPE.METAR )
            new MessageDownloader(type).execute();
        if( type == WeatherReport.TYPE.TAF )
            new MessageDownloader(type).execute();
    }

    public
    void
    refreshMetar() {
        downloadWeatherMessage(WeatherReport.TYPE.METAR);
    }

    public
    void
    refreshTaf(){
        downloadWeatherMessage(WeatherReport.TYPE.TAF);
    }

    //Getters
    public
    String getAirportName() { return mName; }

    public
    String getIcaoCode() { return mIcaoCode; }

    public
    String getLocalCode() { return mLocalCode; }

    public
    Boolean hasTaf() { return mHasTaf; }

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

    private
    class MessageDownloader extends AsyncTask<Void, Void, Void>
    {
        private StringBuilder _finalURL = new StringBuilder();
        private WeatherReport.TYPE _type;
        private String mWeatherMessage;

        MessageDownloader(@NonNull WeatherReport.TYPE type){
            _type = type;
            _finalURL.append(_type.getURL()).append(Airport.this.mIcaoCode);
        }

        @Override
        protected void onPreExecute(){ }

        @Override
        protected Void doInBackground(Void... aVoid) {
            try {
                Document page = Jsoup.connect(_finalURL.toString()).get();
                final Elements elements = page.select("td[width=\"100%\"]");
                final Element el = elements.first();
                if( el != null )
                    mWeatherMessage = el.text();
            }
            catch(Exception e) {e.printStackTrace();}
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if( _type == WeatherReport.TYPE.METAR )
                Airport.this.mMetar = mWeatherMessage;
            if( _type == WeatherReport.TYPE.TAF)
                Airport.this.mtaf = mWeatherMessage;
        }
    }
}
