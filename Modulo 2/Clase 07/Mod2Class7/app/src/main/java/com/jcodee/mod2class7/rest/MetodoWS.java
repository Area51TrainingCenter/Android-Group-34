package com.jcodee.mod2class7.rest;

import com.jcodee.mod2class7.modelos.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by johannfjs on 24/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public interface MetodoWS {

    /*
    GET     -> Obtener datos
    POST    -> Enviar datos
    PUT     -> Actualizar datos (Enviar datos)
    DELETE  -> Eliminar datos (Enviar datos)
     */

    @GET("posts")
    Call<ArrayList<Post>> obtenerPost();
}
