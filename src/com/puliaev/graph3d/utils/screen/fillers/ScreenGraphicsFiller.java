package com.puliaev.graph3d.utils.screen.fillers;

import com.puliaev.graph3d.models.Polygon;
import com.puliaev.graph3d.utils.screen.Filter;
import com.puliaev.graph3d.utils.screen.ScreenConverter;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Заполнитель на графиксе экрана
 */
public abstract class ScreenGraphicsFiller implements Filler {
    private ScreenConverter sc;
    private Graphics2D gr;
    private boolean showGrid;

    /**
     * Создаёт экземпляр заполнителя
     * @param sc преобразователь координат
     * @param gr графикс
     */
    public ScreenGraphicsFiller(ScreenConverter sc, Graphics2D gr) {
        this.sc = sc;
        this.gr = gr;
    }

    @Override
    public void clear(int color) {
        Graphics2D g = getGraphics2D();
        Color c = g.getColor();
        g.setColor(new Color(color));
        g.fillRect(0, 0, sc.getWs(), sc.getHs());
        g.setColor(c);
    }

    @Override
    public void fill(Collection<com.puliaev.graph3d.models.Polygon> polygons) {
        List<com.puliaev.graph3d.models.Polygon> resPolygons = new LinkedList<>();
        Filter<com.puliaev.graph3d.models.Polygon> filter = getFilter();
        for (com.puliaev.graph3d.models.Polygon prevPolygon : polygons) {
            if (filter.permit(prevPolygon))
                resPolygons.add(prevPolygon);
        }
        com.puliaev.graph3d.models.Polygon[] arr = resPolygons.toArray(new com.puliaev.graph3d.models.Polygon[0]);
        Arrays.sort(arr, getComparator());
        for (com.puliaev.graph3d.models.Polygon polygon : arr) {
            oneFill(polygon, isShowGrid());
        }
    }

    protected abstract void oneFill(com.puliaev.graph3d.models.Polygon polygon, boolean isShowGrid);

    /**
     * Должен возвращать фильтр рисуемых полигонов.
     * С помощью него можно оставить только те из них, которые следует рисовать.
     * Например, можно исключить те полигоны, которые находятся "позади"
     * @return фильтр
     */
    protected abstract Filter<Polygon> getFilter();

    /**
     * Должен возвращать компаратор полигонов для упорядочивания их.
     * @return компаратор
     */
    protected abstract Comparator<Polygon> getComparator();

    public ScreenConverter getScreenConverter() {
        return sc;
    }

    public Graphics2D getGraphics2D() {
        return gr;
    }

    @Override
    public boolean isShowGrid() {
        return showGrid;
    }

    @Override
    public void showGrid() {
        this.showGrid = true;
    }

    @Override
    public void hideGrid() {
        this.showGrid = false;
    }
}
