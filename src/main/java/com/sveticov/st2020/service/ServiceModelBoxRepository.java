package com.sveticov.st2020.service;

import com.sveticov.st2020.car_service.Car;
import com.sveticov.st2020.model.ModelBox;
import com.sveticov.st2020.repository.RepositoryModelBox;
import com.sveticov.st2020.repository.RepositorySizeModelBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "job version")
public class ServiceModelBoxRepository implements ServiceSvetikov<ModelBox> {
    private RepositoryModelBox repositoryModelBox;
    private RepositorySizeModelBox repositorySizeModelBox;

    @Autowired
    public ServiceModelBoxRepository(RepositoryModelBox repositoryModelBox, RepositorySizeModelBox repositorySizeModelBox) {
        this.repositoryModelBox = repositoryModelBox;
        this.repositorySizeModelBox = repositorySizeModelBox;
    }

    @Transactional
    @Override
    public void add(ModelBox modelBox) {
        repositoryModelBox.save(modelBox);
        log.info("model box add");
        log.info(modelBox.toString());
    }

    @Transactional
    @Override
    public ModelBox findId(int id) {
        log.info("model " + id);
        log.info(repositoryModelBox.findById(id).get().toString());
        return repositoryModelBox.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public List<ModelBox> findAll() {
        //log.error(repositoryModelBox.findAll().toString());

        return repositoryModelBox.findAll();
    }

    @Transactional
    public void init(List<ModelBox> list) {
        repositoryModelBox.saveAll(list);
    }

    @Transactional
    @Override
    public void show() {
        log.info("show");
        List<ModelBox> list = repositoryModelBox.findAll();
        list.forEach(s -> log.info(s.toString()));
//        repositorySizeModelBox.findAll().forEach(System.out::println);
    }

    @Override
    public void delete(int id) {
        repositoryModelBox.deleteById(id);
    }


}
