package com.sveticov.st2020;

import com.sveticov.st2020.model.ModelBox;
import com.sveticov.st2020.model.SizeModelBox;
import com.sveticov.st2020.service.ServiceModelBox;
import com.sveticov.st2020.service.ServiceModelBoxRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class St2020Application {

    public static void main(String[] args) {
        SpringApplication.run(St2020Application.class, args);
    }


    @Bean
    ApplicationRunner init(ServiceModelBoxRepository serviceModelBox){
        SizeModelBox sizeModelBox1=new SizeModelBox(6,1250,2500);
        SizeModelBox sizeModelBox2=new SizeModelBox(12,2250,2050);
        SizeModelBox sizeModelBox3=new SizeModelBox(16,2750,2090);
        SizeModelBox sizeModelBox4=new SizeModelBox(16,2750,2090);
        SizeModelBox sizeModelBox5=new SizeModelBox(16,2750,2090);
        SizeModelBox sizeModelBox6=new SizeModelBox(16,2750,2090);
        SizeModelBox sizeModelBox7=new SizeModelBox(16,2750,2090);



        List<ModelBox> list= Arrays.asList(new ModelBox("box 1","370px","150px",sizeModelBox7),
                new ModelBox("box 2","370px","210px",sizeModelBox1),
                new ModelBox("box 3","370px","450px",sizeModelBox2),
                new ModelBox("box 4","430px","150px",sizeModelBox3),
                new ModelBox("box 5","430px","210px",sizeModelBox4),
                new ModelBox("box 6","430px","510px",sizeModelBox5),
                new ModelBox("box 7","430px","270px",sizeModelBox6));
       return args -> {

           serviceModelBox.init(list);
           serviceModelBox.show();
       };

    }

}
