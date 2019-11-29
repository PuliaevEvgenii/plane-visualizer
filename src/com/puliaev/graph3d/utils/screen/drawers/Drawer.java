package com.puliaev.graph3d.utils.screen.drawers;

import java.util.Collection;
import com.puliaev.graph3d.models.Polyline;

/**
 * Интерфейс, описывающий в общем виде процесс рисования полилинии
 */
public interface Drawer {
    /**
     * Очищает область заданным цветом
     * @param color цвет
     */
    public void clear(int color);
    
    /**
     * Рисует все полилинии
     * @param polyline набор рисуемых полилиний.
     */
    public void draw(Collection<Polyline> polyline);
}
