package com.jcodee.mod3class1.entidades;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by johannfjs on 31/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class Ubicaciones extends RealmObject {
    @PrimaryKey
    private long id;
    private String titulo;
    private String descripcion;
    private Double latitud;
    private Double longitud;

    public Ubicaciones() {
    }

    public Ubicaciones(long id, String titulo, String descripcion, Double latitud, Double longitud) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
