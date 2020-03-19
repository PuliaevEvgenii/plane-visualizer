package com.puliaev.graph3d.utils.screen.fillers;

import com.puliaev.graph3d.utils.screen.Filter;
import com.puliaev.graph3d.utils.math.Vector3;
import com.puliaev.graph3d.models.Polygon;
import com.puliaev.graph3d.utils.screen.ScreenConverter;
import com.puliaev.graph3d.utils.screen.ScreenCoordinates;
import com.puliaev.graph3d.utils.screen.ScreenPoint;
import com.puliaev.graph3d.utils.screen.colorizers.Colorizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SwingGraphicsFiller extends ScreenGraphicsFiller {
    private Graphics2D graphics2D;

    public SwingGraphicsFiller(ScreenConverter sc, Graphics2D graphics2D, Colorizer colorizer) {
        super(sc, colorizer);
        this.graphics2D = graphics2D;
    }

    @Override
    protected void oneFill(com.puliaev.graph3d.models.Polygon polygon, boolean isShowGrid) {
        getGraphics2D().setColor(getColorizer().getColor(polygon.getzForColor(), 20)); // TODO: 30.11.2019 dZ zForColor
        List<ScreenPoint> sPoints = new ArrayList<>();
        for (Vector3 rPoint : polygon.getPoints()) {
            sPoints.add(getScreenConverter().r2s(rPoint));
        }
        ScreenCoordinates sCoordinates = new ScreenCoordinates(sPoints);
        getGraphics2D().fillPolygon(sCoordinates.getXx(), sCoordinates.getYy(), sCoordinates.size());
        if (isShowGrid) {
            getGraphics2D().setColor(Color.BLACK);
            getGraphics2D().drawPolygon(sCoordinates.getXx(), sCoordinates.getYy(), sCoordinates.size());
        }
    }

    @Override
    public void clear(int color) {
        Graphics2D g = getGraphics2D();
        Color c = g.getColor();
        g.setColor(new Color(color));
        g.fillRect(0, 0, getScreenConverter().getWs(), getScreenConverter().getHs());
        g.setColor(c);
    }

    protected Filter<com.puliaev.graph3d.models.Polygon> getFilter() {
        return new Filter<com.puliaev.graph3d.models.Polygon>() {
            @Override
            public boolean permit(com.puliaev.graph3d.models.Polygon polygon) {
                return true;
            }
        };
    }

    /**
     * Сравниваем полигоны по среднему Z.
     * @return компаратор
     */
    @Override
    protected Comparator<com.puliaev.graph3d.models.Polygon> getComparator() {
        return new Comparator<>() {
            private static final float EPSILON = 1e-10f;
            @Override
            public int compare(com.puliaev.graph3d.models.Polygon o1, Polygon o2) {
                float d = o1.avgZ() - o2.avgZ();
                if (-EPSILON < d && d < EPSILON)
                    return 0;
                return d < 0 ? -1 : 1;
            }
        };
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }
}
