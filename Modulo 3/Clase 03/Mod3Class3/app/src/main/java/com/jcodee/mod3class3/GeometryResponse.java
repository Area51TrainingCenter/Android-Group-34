package com.jcodee.mod3class3;

/**
 * Created by johannfjs on 4/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class GeometryResponse {
    private BoundResponse bounds;
    private CoordenadaResponse location;
    private String location_type;
    private BoundResponse viewport;

    public BoundResponse getBounds() {
        return bounds;
    }

    public void setBounds(BoundResponse bounds) {
        this.bounds = bounds;
    }

    public CoordenadaResponse getLocation() {
        return location;
    }

    public void setLocation(CoordenadaResponse location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public BoundResponse getViewport() {
        return viewport;
    }

    public void setViewport(BoundResponse viewport) {
        this.viewport = viewport;
    }
}
