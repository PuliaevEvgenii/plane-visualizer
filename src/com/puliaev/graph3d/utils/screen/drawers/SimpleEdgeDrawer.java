package com.puliaev.graph3d.utils.screen.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.LinkedList;

import com.puliaev.graph3d.utils.screen.Filter;
import com.puliaev.graph3d.utils.screen.ScreenConverter;
import com.puliaev.graph3d.utils.math.Vector3;
import com.puliaev.graph3d.utils.screen.ScreenCoordinates;
import com.puliaev.graph3d.utils.screen.ScreenPoint;
import com.puliaev.graph3d.models.Polyline;

/**
 * Реализация рисователя полигонов с помощью рёбер.
 */
public class SimpleEdgeDrawer extends ScreenGraphicsDrawer {

    public SimpleEdgeDrawer(ScreenConverter sc, Graphics2D g) {
        super(sc, g);
    }

    /**
     * Рисует одну полилинию на графиксе.
     * @param polyline полилиния
     */
    @Override
    protected void oneDraw(Polyline polyline) {
        LinkedList<ScreenPoint> sPoints = new LinkedList<>();
        /*переводим все точки в экранные*/
        for (Vector3 v : polyline.getPoints())
            sPoints.add(getScreenConverter().r2s(v));
        getGraphics2D().setColor(Color.BLACK);

        /*если точек меньше двух, то рисуем отдельными алгоритмами*/
        if (sPoints.size() < 2) {
            if (sPoints.size() > 0)
                getGraphics2D().fillRect(sPoints.get(0).getI(), sPoints.get(0).getJ(), 1, 1);
            return;
        }

        /*создаём хранилище этих точек в виде двух массивов*/
        ScreenCoordinates crds = new ScreenCoordinates(sPoints);

        getGraphics2D().drawPolyline(crds.getXx(), crds.getYy(), crds.size());
    }

    /**
     * В данной реализации возвращаем фильтр, который одобряет все полилинии.
     * @return фильтр полилиний
     */
    @Override
    protected Filter<Polyline> getFilter() {
        return new Filter<Polyline>() {
            @Override
            public boolean permit(Polyline line) {
                return true;
            }
        };
    }

    /**
     * Сравниваем полилинии по среднему Z.
     * @return компаратор
     */
    @Override
    protected Comparator<Polyline> getComparator() {
        return new Comparator<Polyline>() {
            private static final float EPSILON = 1e-10f;
            @Override
            public int compare(Polyline o1, Polyline o2) {
                float d = o1.avgZ() - o2.avgZ();
                if (-EPSILON < d && d < EPSILON)
                    return 0;
                return d < 0 ? -1 : 1;
            }
        };
    }
    
}
