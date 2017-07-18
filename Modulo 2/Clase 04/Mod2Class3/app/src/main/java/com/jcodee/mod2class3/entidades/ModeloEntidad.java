package com.jcodee.mod2class3.entidades;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by johannfjs on 14/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class ModeloEntidad extends RealmObject {
    @PrimaryKey
    private long id;
    private String descripcion;
    private MarcaEntidad marca;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MarcaEntidad getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntidad marca) {
        this.marca = marca;
    }
}
