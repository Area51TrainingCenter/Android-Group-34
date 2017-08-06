package com.jcodee.mod3class3;

/**
 * Created by johannfjs on 4/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class BoundResponse {
    private CoordenadaResponse northeast;
    private CoordenadaResponse southwest;

    public CoordenadaResponse getNortheast() {
        return northeast;
    }

    public void setNortheast(CoordenadaResponse northeast) {
        this.northeast = northeast;
    }

    public CoordenadaResponse getSouthwest() {
        return southwest;
    }

    public void setSouthwest(CoordenadaResponse southwest) {
        this.southwest = southwest;
    }
}
