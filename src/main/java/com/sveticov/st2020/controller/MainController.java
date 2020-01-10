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

    private SimulatorCarDirection simulatorCarDirection;

    @Autowired
    public MainController(@Qualifier(value = "job version") ServiceSvetikov<ModelBox> serviceModelBox, SimulatorCarDirection simulatorCarDirection) {
        this.serviceModelBox = serviceModelBox;
        this.simulatorCarDirection = simulatorCarDirection;
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
    public void add(@PathVariable("z_position") String z_position,@PathVariable("x_position") String x_position){
              serviceModelBox.add(new ModelBox("box z",x_position,z_position,new SizeModelBox(1,1,1)));
    }

    @GetMapping("/show")
    public void show() {
        serviceModelBox.show();
    }


    @GetMapping("/car/{z_position}/{x_position}")
    public ResponseEntity<PositionCar> directionalCar1(@PathVariable("z_position")int z_position, @PathVariable("x_position") int x_position) {
log.info(z_position+"  " +x_position);
        return new ResponseEntity<>(simulatorCarDirection.direction(z_position, x_position), HttpStatus.OK);
    }
    @GetMapping("/car/init")
    public ResponseEntity<PositionCar> initOldPosition(){
        return new ResponseEntity<>(simulatorCarDirection.initOldPosition(),HttpStatus.OK);
    }



}
