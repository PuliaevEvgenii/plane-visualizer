package com.company.third_dim;

import com.company.utils.math.Vector3;

/**
 * Описывает основную функциональность камеры - превращение координат 
 * из мировой системы координат в систему координат камеры.
 */
public interface Camera {
    /**
     * Преобразует точку из мировой системы координат в систему координат камеры
     * @param v преобразуемая точка
     * @return новая точка
     */
    public Vector3 w2s(Vector3 v);
}
