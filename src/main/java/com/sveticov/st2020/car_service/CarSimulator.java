package com.sveticov.st2020.car_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarSimulator implements SimulatorCarDirection {
    private int z_position_last = 0;
    private int x_position_last = 25;
   private boolean lock;

    public PositionCar direction(int z_position, int x_position) {
        synchronized (this) {
            if (x_position_last < x_position) {
                if (z_position_last == 70) {
                    log.info(x_position_last + " < " + x_position);
                    x_position_last = x_position_last + 5;
                    lock=false;
                }
                if(z_position_last!=70){
                    z_position=70;
                    lock=true;
                }
            }
        }
        synchronized (this) {
            if (x_position_last > x_position) {
                if (z_position_last == 70) {
                    log.info(x_position_last + " > " + x_position);
                    x_position_last = x_position_last - 5;
                    lock=false;
                }
                if(z_position_last!=70){
                    z_position=70;
                    lock=true;
                }
            }
        }

        if (x_position == x_position_last||lock) {
            log.info(x_position_last + " == " + x_position);
            if (z_position < z_position_last) {
                z_position_last = z_position_last - 5;
            }
            if (z_position > z_position_last) {
                z_position_last = z_position_last + 5;
            }
        }
        log.info("x_pos_last " + x_position_last);
        log.info("position x " + x_position);

        return new PositionCar(z_position_last + "px", x_position_last + "px");
    }

    @Override
    public PositionCar initOldPosition() {
        return new PositionCar(z_position_last+"px",x_position_last+"px");
    }
}
