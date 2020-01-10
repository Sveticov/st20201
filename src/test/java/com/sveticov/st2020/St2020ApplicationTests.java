package com.sveticov.st2020;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class St2020ApplicationTests {

    String host="http://localhost:8080/app/info/";

    @Test
    void contextLoads() {

        RestTemplate restTemplate=new RestTemplate();
    ResponseEntity<String> responseEntity= restTemplate.getForEntity(host+(1),String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody());

    }

}
