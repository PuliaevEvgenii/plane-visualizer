package com.company.models;

import java.util.List;

/**
 * Описывает в общем виде любую модель
 */
public interface Model {
    /**
     * Любая модель - это набор полилиний.
     * @return Списко полилиний модели.
     */
    List<Polyline> getLines();
}
