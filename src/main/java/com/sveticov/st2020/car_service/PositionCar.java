package com.sveticov.st2020.car_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionCar {
    private String z_position;
    private String x_position;
}
