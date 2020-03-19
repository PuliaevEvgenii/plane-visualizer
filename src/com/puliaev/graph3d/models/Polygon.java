package com.puliaev.graph3d.models;

import com.puliaev.graph3d.utils.math.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Polygon extends Polyline {
    private float zForColor;

    public Polygon(Collection<Vector3> points) {
        super(points, true);
    }

    public Polygon(Collection<Vector3> points, float zForColor) {
        super(points, true);
        this.zForColor = zForColor;
    }

    @Override
    public List<Polyline> getLines() {
        return new ArrayList<>(Collections.singletonList(new Polyline(getPoints(), true)));
    }

    public float getzForColor() {
        return zForColor;
    }
}
