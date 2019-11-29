package com.company.models;

import java.util.ArrayList;
import java.util.List;

public class Surface implements Model {
    private List<Polygon> polygons;

    public Surface(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    @Override
    public List<Polyline> getLines() {
        return new ArrayList<>(polygons);
    }
}
