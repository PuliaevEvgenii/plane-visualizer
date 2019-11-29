package com.puliaev.graph3d.utils.screen.colorizers;

import java.awt.Color;

public interface Colorizer {
    Color getColor(float z, float dZ);
}
