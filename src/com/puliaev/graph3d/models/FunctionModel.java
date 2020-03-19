package com.puliaev.graph3d.models;

import com.puliaev.graph3d.utils.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class FunctionModel implements Model {

    public interface Function {
        float getZ(float x, float y);
    }

    private Function function; // TODO: 30.11.2019 zBuffer
    private float xInterval;
    private float yInterval;
    private Vector3 startPoint;
    private Vector3 endPoint;

    public FunctionModel(Function function, int xNumCom, int yNumCom, Vector3 startPoint, Vector3 endPoint) {
        this.function = function;
        this.xInterval = Math.abs(startPoint.getX() - endPoint.getX()) / xNumCom;
        this.yInterval = Math.abs(startPoint.getY() - endPoint.getY()) / yNumCom;;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public List<Polyline> getLines() {
        List<Polygon> polygons = new ArrayList<>();

        for (float x = startPoint.getX(); x < endPoint.getX() - xInterval; x += xInterval) {

            float z1 = function.getZ(x, startPoint.getY());
            float z2 = function.getZ(x + xInterval, startPoint.getY());

            for (float y = startPoint.getY(); y < endPoint.getY() - yInterval; y += yInterval) {

                float z3 = function.getZ(x + xInterval, y + yInterval);
                float z4 = function.getZ(x, y + yInterval);

                if (isInZone(z1, z2, z4)) {

                    z1 = correctZ(z1);
                    z2 = correctZ(z2);
                    z4 = correctZ(z4);

                    polygons.add(new Polygon(
                            Arrays.asList(
                                    new Vector3(x, y, z1),
                                    new Vector3(x + xInterval, y, z2),
                                    new Vector3(x, y + yInterval, z4)
                            ),
                            (z1 + z2 + z3) / 3
                    ));
                }
                if (isInZone(z2, z3, z4)) {

                    z3 = correctZ(z3);
                    z2 = correctZ(z2);
                    z4 = correctZ(z4);

                    polygons.add(new Polygon(
                            Arrays.asList(
                                    new Vector3(x + xInterval, y, z2),
                                    new Vector3(x + xInterval, y + yInterval, z3),
                                    new Vector3(x, y + yInterval, z4)
                            ),
                            (z1 + z2 + z3) / 3
                    ));
                }

                z1 = z4;
                z2 = z3;
            }
        }
        Surface surface = new Surface(polygons);
        return surface.getLines();
    }

    private float correctZ(float z1) {
        z1 = min(max(z1, startPoint.getZ()), endPoint.getZ());
        return z1;
    }

    private boolean isInZone(float z1, float z2, float z3) {

        if ((z1 > startPoint.getZ() && z1 < endPoint.getZ()) ||
                (z2 > startPoint.getZ() && z2 < endPoint.getZ()) ||
                (z3 > startPoint.getZ() && z3 < endPoint.getZ())) {
            return true;
        }

        return false;
        /*return (z1 > startPoint.getZ() && z1 < endPoint.getZ()) &&
                (z2 > startPoint.getZ() && z2 < endPoint.getZ()) &&
                (z3 > startPoint.getZ() && z3 < endPoint.getZ()) &&
                (z4 > startPoint.getZ() && z4 < endPoint.getZ());*/
    }
}
