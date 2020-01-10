package com.sveticov.st2020.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sizebox")
public class SizeModelBox implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_size")
    private int id_size;
    private int thickness;
    private int height;
    private int width;
    @OneToOne(mappedBy = "sizeModelBox",cascade = CascadeType.ALL,orphanRemoval = true)
    private ModelBox modelBox;

    public SizeModelBox(ModelBox modelBox) {
        this.modelBox = modelBox;
    }



    public SizeModelBox(int thickness, int height, int width) {

        this.thickness = thickness;
        this.height = height;
        this.width = width;
    }

    public SizeModelBox() {
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }



    public int getId_size() {
        return id_size;
    }

    public void setId_size(int id_size) {
        this.id_size = id_size;
    }

    @Override
    public String toString() {
        return "SizeModelBox{" +
                "id_size=" + id_size +
                ", thickness=" + thickness +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
