package com.sveticov.st2020.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="model")
public class ModelBox implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_box")
    private int idBox;

    private String nameBox;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="id_size")
    private SizeModelBox sizeModelBox;

    private String positionTop;
    private String positionLeft;

    public ModelBox() {
    }

    public ModelBox( String nameBox,  String positionTop, String positionLeft) {

        this.nameBox = nameBox;

        this.positionTop = positionTop;
        this.positionLeft = positionLeft;
    }

    public ModelBox( String nameBox,  String positionTop, String positionLeft,SizeModelBox sizeModelBox) {
        this.sizeModelBox = sizeModelBox;
        this.nameBox = nameBox;

        this.positionTop = positionTop;
        this.positionLeft = positionLeft;
    }


    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }


    public String getNameBox() {
        return nameBox;
    }

    public void setNameBox(String nameBox) {
        this.nameBox = nameBox;
    }


    public SizeModelBox getSizeModelBox() {
        return sizeModelBox;
    }

    public void setSizeModelBox(SizeModelBox sizeModelBox) {
        this.sizeModelBox = sizeModelBox;
    }


    public String getPositionTop() {
        return positionTop;
    }

    public void setPositionTop(String positionTop) {
        this.positionTop = positionTop;
    }

    public String getPositionLeft() {
        return positionLeft;
    }

    public void setPositionLeft(String positionLeft) {
        this.positionLeft = positionLeft;
    }

    @Override
    public String toString() {
        return "ModelBox{" +
                "idBox=" + idBox +
                ", nameBox='" + nameBox + '\'' +
                ", sizeModelBox=" + sizeModelBox.toString() +
                ", positionTop='" + positionTop + '\'' +
                ", positionLeft='" + positionLeft + '\'' +
                '}';
    }
}
