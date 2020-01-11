package com.sveticov.st2020.car_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("car2")
@Slf4j
public class CarSimulator2 implements SimulatorCarDirection {
    private int z_position_last = 700;
    private int x_position_last = 25;
    private boolean lock;
@Override
    public PositionCar direction(int z_position, int x_position) {
        synchronized (this) {
            if (x_position_last < x_position) {
                if (z_position_last == 700) {
//                    log.info(x_position_last + " < " + x_position);
                    x_position_last = x_position_last + 5;
                    lock=false;
                }
                if(z_position_last!=700){
                    z_position=700;
                    lock=true;
                }
            }
        }
        synchronized (this) {
            if (x_position_last > x_position) {
                if (z_position_last == 700) {
//                    log.info(x_position_last + " > " + x_position);
                    x_position_last = x_position_last - 5;
                    lock=false;
                }
                if(z_position_last!=700){
                    z_position=700;
                    lock=true;
                }
            }
        }

        if (x_position == x_position_last||lock) {
//            log.info(x_position_last + " == " + x_position);
            if (z_position < z_position_last) {
                z_position_last = z_position_last - 5;
            }
            if (z_position > z_position_last) {
                z_position_last = z_position_last + 5;
            }
        }
//        log.info("x_pos_last " + x_position_last);
//        log.info("position x " + x_position);

        return new PositionCar(z_position_last + "px", x_position_last + "px");
    }

    @Override
    public PositionCar initOldPosition() {
        return new PositionCar(z_position_last+"px",x_position_last+"px");
    }

    @Override
    public int get_x() {
        return this.x_position_last;
    }

    @Override
    public int get_z() {
        return this.z_position_last;
    }


}
