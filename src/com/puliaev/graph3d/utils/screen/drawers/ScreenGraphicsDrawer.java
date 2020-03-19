package com.puliaev.graph3d.utils.screen.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.puliaev.graph3d.utils.screen.Filter;
import com.puliaev.graph3d.utils.screen.ScreenConverter;
import com.puliaev.graph3d.models.Polyline;

/**
 * Рисовальщик на графиксе экрана
 */
public abstract class ScreenGraphicsDrawer implements Drawer {
    private ScreenConverter sc;

    /**
     * Создаёт экземпляр рисвальщика
     * @param sc преобразователь координат
     */
    public ScreenGraphicsDrawer(ScreenConverter sc) {
        this.sc = sc;
    }
    
    @Override
    public void draw(Collection<Polyline> polylines) {
        List<Polyline> lines = new LinkedList<>();
        Filter<Polyline> filter = getFilter();
        for (Polyline pl : polylines) {
            if (filter.permit(pl))
                lines.add(pl);
        }
        Polyline[] arr = lines.toArray(new Polyline[0]);
        Arrays.sort(arr, getComparator());
        for (Polyline pl : arr) {
            oneDraw(pl);
        }
    }

    /**
     * Метод, умеющий рсовать одну полилинию
     * @param polyline полилиния, которую требуется нарисовать
     */
    protected abstract void oneDraw(Polyline polyline);

    /**
     * Должен возвращать фильтр рисуемых полилиний.
     * С помощью него можно оставить только те из них, которые следует рисовать.
     * Например, можно исключить те линии, которые находятся "позади"
     * @return фильтр
     */
    protected abstract Filter<Polyline> getFilter();
    
    /**
     * Должен возвращать компаратор полилиний для упорядочивания их.
     * @return компаратор
     */
    protected abstract Comparator<Polyline> getComparator();

    public ScreenConverter getScreenConverter() {
        return sc;
    }

}
