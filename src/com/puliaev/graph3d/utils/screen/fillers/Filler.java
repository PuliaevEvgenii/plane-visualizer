package com.puliaev.graph3d.utils.screen.fillers;

import com.puliaev.graph3d.models.Polygon;

import java.util.Collection;

/**
 * Интерфейс, описывающий в общем виде процесс заполнения полигона
 */
public interface Filler {
    /**
     * Очищает область заданным цветом
     * @param color цвет
     */
    void clear(int color);

    /**
     * заполняет полигон
     * @param polygon набор рисуемых полилиний.
     */
    void fill(Collection<Polygon> polygon);

    boolean isShowGrid();

    void showGrid();

    void hideGrid();
}
