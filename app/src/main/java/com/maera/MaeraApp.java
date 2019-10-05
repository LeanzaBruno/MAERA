package com.maera;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

public final class MaeraApp extends Application {
    private Activity _currentActivity = null;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public Activity getCurrentActivity(){
        return _currentActivity;
    }

    public void setCurrentActivity(@NonNull Activity activity){
        _currentActivity = activity;
    }





}
