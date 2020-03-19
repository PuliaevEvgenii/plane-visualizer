package com.puliaev.graph3d.third_dim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.puliaev.graph3d.models.Polygon;
import com.puliaev.graph3d.utils.screen.drawers.Drawer;
import com.puliaev.graph3d.utils.math.Vector3;
import com.puliaev.graph3d.models.Model;
import com.puliaev.graph3d.models.Line;
import com.puliaev.graph3d.models.Polyline;
import com.puliaev.graph3d.utils.screen.fillers.Filler;

/**
 * Описывает трёхмерную со всеми объектами на ней
 */
public class Scene {
    private List<Model> models = new ArrayList<>();
    private int backgroundColor;
    private boolean showAxes;
    private static final List<Line> axes = Arrays.asList(
            new Line(new Vector3(0, 0, 0), new Vector3(1, 0, 0)),
            new Line(new Vector3(0, 0, 0), new Vector3(0, 1, 0)),
            new Line(new Vector3(0, 0, 0), new Vector3(0, 0, 1))
    );

    /**
     * Создаём сцену с заданным фоном
     * @param backgroundColorRGB цвет фона.
     */
    public Scene(int backgroundColorRGB) {
        this.backgroundColor = backgroundColorRGB;
        this.showAxes = false;
    }

    /**
     * Рисуем сцену со всеми моделями
     * @param drawer то, с помощью чего будем рисовать
     * @param cam камера для преобразования координат
     */
    public void drawScene(Drawer drawer, Filler filler, Camera cam) {
        List<Polyline> lines = new LinkedList<>();
        List<Polygon> polygons = new LinkedList<>();

        List<Collection<? extends Model>> tempModelsColl = new LinkedList<>();
        tempModelsColl.add(models);
        if (isShowAxes())
            tempModelsColl.add(axes);

        for (Collection<? extends Model> tempModels : tempModelsColl)
            for (Model model : tempModels) {
                for (Polyline pl : model.getLines()) {



                    /*Все точки конвертируем с помощью камеры*/
                    List<Vector3> sPoints = new LinkedList<>();
                    for (Vector3 rPoints : pl.getPoints()) {
                        sPoints.add(cam.w2s(rPoints));
                    }
                    /*Создаём на их сонове новые полилинии, но в том виде, в котором их видит камера*/
                    if (pl instanceof Polygon)

                        polygons.add(new Polygon(sPoints, ((Polygon) pl).getzForColor()));
                    else
                        lines.add(new Polyline(sPoints, pl.isClosed()));
                }
            }
        /*Закрашиваем фон*/
        drawer.clear(backgroundColor);
        /*Рисуем все линии*/
        filler.fill(polygons);
        drawer.draw(lines);
    }

    public boolean isShowAxes() {
        return showAxes;
    }
    
    public void showAxes() {
        this.showAxes = true;
    }
    
    public void hideAxes() {
        this.showAxes = false;
    }

    public List<Model> getModelsList() {
        return models;
    }
}
