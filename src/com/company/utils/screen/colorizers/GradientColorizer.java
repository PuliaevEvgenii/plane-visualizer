package com.company.utils.screen.colorizers;

import java.awt.*;

public class GradientColorizer implements Colorizer {
    private Color startColor;
    private Color middleColor;
    private Color endColor;

    public GradientColorizer() {
        this.startColor = Color.BLUE;
        this.middleColor = Color.GREEN;
        this.endColor = Color.RED;
    }

    @Override
    public Color getColor(float z, float dZ) {
        Color thisStartColor = startColor;
        Color thisEndColor = endColor;

        dZ = Math.abs(dZ / 2);
        if (z < 0) {
            z += dZ;
            thisEndColor = middleColor;
        } else {
            thisStartColor = middleColor;
        }
        float k = z / dZ;

        int r = (int) ((1 - k) * thisStartColor.getRed() + k * thisEndColor.getRed());
        int g = (int) ((1 - k) * thisStartColor.getGreen() + k * thisEndColor.getGreen());
        int b = (int) ((1 - k) * thisStartColor.getBlue() + k * thisEndColor.getBlue());

        r = Math.max(Math.min(r, 255), 0);
        g = Math.max(Math.min(g, 255), 0);
        b = Math.max(Math.min(b, 255), 0);
        return new Color(r, g, b);
    }
}
