package com.jcodee.mod2class7.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johannfjs on 24/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class HelperWS {
    public static Retrofit obtenerConfiguracion() {
        return new Retrofit.Builder()
                //Ruta base del web service
                .baseUrl("https://jsonplaceholder.typicode.com/")
                //Especificamos en que está devolviendonos la info el web service
                .addConverterFactory(GsonConverterFactory.create())
                //Construimos la configuración de retrofit
                .build();
    }
}
