package com.sveticov.st2020.car_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int idCar;
    private int z_position;
    private int x_position;
    private boolean y_position;
}
