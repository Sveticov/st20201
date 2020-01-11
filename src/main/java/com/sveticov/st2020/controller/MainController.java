package com.sveticov.st2020.controller;

import com.sveticov.st2020.car_service.Car;
import com.sveticov.st2020.car_service.PositionCar;
import com.sveticov.st2020.car_service.SimulatorCarDirection;
import com.sveticov.st2020.model.ModelBox;
import com.sveticov.st2020.model.SizeModelBox;
import com.sveticov.st2020.service.ServiceModelBox;
import com.sveticov.st2020.service.ServiceSvetikov;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/app")
public class MainController {
    @Qualifier(value = "job version")
    private ServiceSvetikov<ModelBox> serviceModelBox;
    @Qualifier("car1")
    private SimulatorCarDirection simulatorCarDirection;
    @Qualifier("car2")
    private SimulatorCarDirection simulatorCarDirection2;

    @Autowired
    public MainController(@Qualifier(value = "job version") ServiceSvetikov<ModelBox> serviceModelBox, @Qualifier("car1") SimulatorCarDirection simulatorCarDirection,
                          @Qualifier("car2") SimulatorCarDirection simulatorCarDirection2) {
        this.serviceModelBox = serviceModelBox;
        this.simulatorCarDirection = simulatorCarDirection;
        this.simulatorCarDirection2 = simulatorCarDirection2;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/info/{id}")
    public ResponseEntity<String> info(@PathVariable int id) throws Exception {
        if (id < 0) {
            throw new Exception("id not correct");
        }
        return new ResponseEntity<>("info documents " + id, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/box/{idBox}")
    public ResponseEntity<ModelBox> getBox(@PathVariable int idBox) {

        return new ResponseEntity<>(this.serviceModelBox.findId(idBox), HttpStatus.OK);
    }

    @GetMapping("/boxes")
    public ResponseEntity<List<ModelBox>> getAllModelBox() {
        log.info("getAll Box");

        return new ResponseEntity<>(serviceModelBox.findAll(), HttpStatus.OK);
    }

    @GetMapping("/add/{z_position}/{x_position}")
    public void add(@PathVariable("z_position") String z_position, @PathVariable("x_position") String x_position) {
        serviceModelBox.add(new ModelBox("box z", x_position, z_position, new SizeModelBox(1, 1, 1)));
    }

    @GetMapping("/show")
    public void show() {
        serviceModelBox.show();
    }


    @GetMapping("/car/{z_position}/{x_position}")
    public ResponseEntity<PositionCar> directionalCar1(@PathVariable("z_position") int z_position, @PathVariable("x_position") int x_position) {
     //   log.info(z_position + "  " + x_position);
        return new ResponseEntity<>(simulatorCarDirection.direction(z_position, x_position), HttpStatus.OK);
    }

    @GetMapping("/car2/{z_position}/{x_position}")
    public ResponseEntity<PositionCar> directionalCar2(@PathVariable("z_position") int z_position, @PathVariable("x_position") int x_position) {
      //  log.info(z_position + "  " + x_position);

        return new ResponseEntity<>(simulatorCarDirection2.direction(z_position, x_position), HttpStatus.OK);
    }

    @GetMapping("/car2/found/board")
    public ResponseEntity<Boolean> deleteFoundBoard() {
        boolean status=false;
        String car_x=simulatorCarDirection2.get_x()+"px";
        String car_z=simulatorCarDirection2.get_z()+"px";
        int id = serviceModelBox.findAll().stream()
                .filter(board -> {
                    log.info(board.getPositionLeft()+"  "+board.getPositionTop());

                    if (board.getPositionLeft().compareTo(car_z)==0 &&
                            board.getPositionTop().compareTo(car_x)==0) {

                        return true;
                    }
                    return false;
                })
                .findFirst()
                .map(board -> board.getIdBox())
                .orElse(-1);
        if (id != -1){
            serviceModelBox.delete(id);
            status=true;
        }

log.info("id "+id+"  "+car_z+"  "+car_x+"  "+status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @GetMapping("/car/init")
    public ResponseEntity<PositionCar> initOldPosition() {
        return new ResponseEntity<>(simulatorCarDirection.initOldPosition(), HttpStatus.OK);
    }


}
