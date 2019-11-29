package com.company.models;

import java.util.Arrays;
import java.util.List;
import com.company.utils.math.Vector3;

/**
 * Описывает трёхмерный отрезок
 */
public class Line implements Model {
    private Vector3 p1, p2;

    public Line(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public List<Polyline> getLines() {
        return Arrays.asList(new Polyline(Arrays.asList(p1, p2), false));
    }
    
}
