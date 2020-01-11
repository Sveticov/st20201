package com.sveticov.st2020.service;

import com.sveticov.st2020.model.ModelBox;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceModelBox implements ServiceSvetikov<ModelBox> {
    List<ModelBox> modelBoxes=new ArrayList<>();

    @Override
    public void add(ModelBox modelBox) {
        modelBoxes.add(modelBox);
    }

    @Override
    public ModelBox findId(int id) {

        return modelBoxes.stream()
                .filter(modelBox -> modelBox.getIdBox()==id)
                .findFirst().orElse(null);

    }

    @Override
    public List<ModelBox> findAll() {
        return modelBoxes;
    }

    @Transactional
    @Override
    public void show() {

    }

    @Override
    public void delete(int id) {

    }


    public void init(List<ModelBox> modelBoxes){
        this.modelBoxes=modelBoxes;
    }
}
