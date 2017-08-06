package com.jcodee.mod3class3.rest;

import com.jcodee.mod3class3.DireccionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johannfjs on 4/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public interface MetodoWS {

    // https://maps.googleapis.com/maps/api/geocode/json?
    // address=1600+Amphitheatre+Parkway,+Mountain+View,+CA
    // &key=YOUR_API_KEY
    // &components=country:PE
    // &latlng=12.42364,34.742354

    @GET("geocode/json")
    Call<DireccionResponse> buscarDireccion(@Query("address") String direccion,
                                            @Query("key") String llave,
                                            @Query("components") String componente);
}
