package com.sveticov.st2020.service;

import com.sveticov.st2020.car_service.Car;

import java.util.List;

public interface ServiceSvetikov<T> {
    void add(T t);
    T findId(int id);
    List<T> findAll();

    void show();
    void delete(int id);


}
