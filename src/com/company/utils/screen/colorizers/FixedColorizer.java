package com.company.utils.screen.colorizers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedColorizer implements Colorizer {
    private List<Color> colors;
    private int interval;

    public FixedColorizer(Color... colorArr) {
        colors = new ArrayList<>();
        Collections.addAll(colors, colorArr);
        this.interval = colors.size();
    }

    public FixedColorizer() {
        this.colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        colors.add(Color.RED);
        this.interval = colors.size();
    }

    @Override
    public Color getColor(float z, float dZ) {
        dZ = Math.abs(dZ);
        z += dZ / 2;
        float k = z / dZ;
        int c = Math.round(k * interval) < interval ? Math.round(k * interval) : interval - 1;
        return colors.get(c);
    }
}
