package com.sveticov.st2020.car_service;

public interface SimulatorCarDirection {
 PositionCar direction(int z, int x);
 PositionCar initOldPosition();
 int get_x();
 int get_z();
}
