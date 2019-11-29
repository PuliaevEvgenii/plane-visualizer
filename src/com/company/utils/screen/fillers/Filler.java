package com.company.utils.screen.fillers;

import com.company.models.Polygon;

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
     * @param polygone набор рисуемых полилиний.
     */
    void fill(Collection<Polygon> polygon);

    boolean isShowGrid();

    void showGrid();

    void hideGrid();
}
