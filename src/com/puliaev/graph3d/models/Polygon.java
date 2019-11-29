package com.puliaev.graph3d.models;

import com.puliaev.graph3d.utils.math.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Polygon extends Polyline implements Model {
    private Color color;

    public Polygon(Collection<Vector3> points, Color color) {
        super(points, true);
        this.color = color;
    }

    @Override
    public List<Polyline> getLines() {
        return new ArrayList<>(Collections.singletonList(new Polyline(getPoints(), true)));
    }

    public Color getColor() {
        return color;
    }
}
