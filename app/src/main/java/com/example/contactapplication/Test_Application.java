package com.example.contactapplication;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;


public class Test_Application extends Application {

    public static final String APPLICATION_ID = "86434E30-99A1-3234-FF14-B71B7DF83E00";
    public static final String API_KEY = "E4D51552-988D-43EA-A688-651E8CF82E0E";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static BackendlessUser user;
    public  static List<Contact_Table> contacts;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );
    }
}
