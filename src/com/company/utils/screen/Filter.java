package com.company.utils.screen;

/**
 * Интерфейс декларирует метод, который будет отвечать, подходит ли заданный экземпляр класса какому-либо условию
 */
public interface Filter<T> {
    boolean permit(T value);
}
