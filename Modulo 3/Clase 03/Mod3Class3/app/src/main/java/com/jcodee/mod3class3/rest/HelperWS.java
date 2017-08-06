package com.jcodee.mod3class3.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johannfjs on 4/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class HelperWS {

    // https://maps.googleapis.com/maps/api/geocode/json?
    // address=1600+Amphitheatre+Parkway,+Mountain+View,+CA
    // &key=YOUR_API_KEY

    public static Retrofit obtenerConfiguracion() {
        return new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
