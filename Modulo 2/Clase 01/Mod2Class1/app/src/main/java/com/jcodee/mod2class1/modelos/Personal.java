package com.jcodee.mod2class1.modelos;

/**
 * Created by johannfjs on 10/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class Personal {
    private int id;
    private String empresa, cargo, nombre;

    public Personal() {
    }

    /*
        Personal personal = new Personal();
        personal.setId(1);
        personal.setCargo("");
        personal.setEmpresa("");
        personal.setNombre("");

        Personal personal1 = new Personal("empresa", "cargo", "nombre");
        personal1.setId(2);
     */

    public Personal(String empresa, String cargo, String nombre) {
        this.empresa = empresa;
        this.cargo = cargo;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
