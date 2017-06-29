package com.jcodee.mod1class5.modelos;

/**
 * Created by johannfjs on 28/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Noticia {
    private int id;
    private String titulo;
    private String seccion;
    private String contenido;
    //GETTER -> para poder obtener datos
    //SETTER -> para poder cambiar datos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
