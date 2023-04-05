package com.socks.socksCounter.entity;



import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String color;
    private int cottonPart;

//    public Socks(String color, int cottonPart) throws Exception {
//        if (cottonPart<0) {
//            throw new Exception("cotton part should be more than 0");
//        }
//        if (cottonPart>100) {
//            throw new Exception("cotton part cannot be more than 100");
//        }
//    }

    public Socks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && color.equals(socks.color);
    }

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);


    }
}

