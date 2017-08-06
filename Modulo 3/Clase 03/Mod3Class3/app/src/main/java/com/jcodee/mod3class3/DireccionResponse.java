package com.jcodee.mod3class3;

import java.util.ArrayList;

/**
 * Created by johannfjs on 4/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class DireccionResponse {
    private String status;
    private ArrayList<DetalleResponse> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<DetalleResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<DetalleResponse> results) {
        this.results = results;
    }
}
